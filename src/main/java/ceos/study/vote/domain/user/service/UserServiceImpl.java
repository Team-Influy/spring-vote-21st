package ceos.study.vote.domain.user.service;

import ceos.study.vote.domain.user.converter.UserConverter;
import ceos.study.vote.domain.user.dto.UserRequestDto;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.domain.user.repository.UserRepository;
import ceos.study.vote.global.apiPayload.code.status.ErrorStatus;
import ceos.study.vote.global.apiPayload.exception.GeneralException;
import ceos.study.vote.global.jwt.TokenProvider;
import ceos.study.vote.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final long REFRESH_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60 * 7; // refresh token: 1주일
    private final TokenProvider tokenProvider;
    private final RedisService redisService;

    @Override
    @Transactional
    public User signUp(UserRequestDto.SignUpRequestDto request) {
        // 이미 유저가 있는지 확인 (이메일 안 겹치는지 확인)
        User existingUser = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (existingUser != null) throw new GeneralException(ErrorStatus.USER_ALREADY_EXISTS);

        User newUser = UserConverter.toUser(request, passwordEncoder);
        userRepository.save(newUser);

        return newUser;
    }

    @Override
    @Transactional
    public TokenProvider.TokenPair signIn(UserRequestDto.SignInRequestDto request) {
        // 회원가입 했는지 확인
        // 패스워드 맞는지 확인
        User existingUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
            throw new GeneralException(ErrorStatus.WRONG_PASSWORD);
        }

        // 토큰 발행
        String accessToken = tokenProvider.createAccessToken(request.getEmail());
        String refreshToken = tokenProvider.createRefreshToken(request.getEmail());

        // Redis에 refresh token 저장
        redisService.setValue(existingUser.getEmail(), refreshToken, 1000 * REFRESH_TOKEN_VALIDITY_SECONDS); // Timeout이 밀리초라 1000 곱하슈

        return new TokenProvider.TokenPair(existingUser, accessToken, refreshToken);
    }

    @Override
    @Transactional
    public void signOut(String accessToken, User user) {
        if (user == null) throw new GeneralException(ErrorStatus.USER_NOT_FOUND);

        // 로그아웃 시 refreshToken을 redis에서 삭제하고 accessToken을 redis에 저장
        redisService.deleteValue(user.getEmail()); // 재로그인 방지
        redisService.setValue(accessToken, "logout", tokenProvider.getExpirationTime(accessToken)); // AccessToken을 블랙리스트에 저장
    }

    @Override
    public TokenProvider.TokenPair reissue(UserRequestDto.ReissueRequestDto request, User user) {
        if (user == null) throw new GeneralException(ErrorStatus.USER_NOT_FOUND);
        String refreshToken = request.getRefreshToken();

        // ValidateToken false 반환 -> 사용하려는 refreshToken이 유효하지 않음 (redis에 없음)
        if (!tokenProvider.validateToken(refreshToken)) throw new GeneralException(ErrorStatus.INVALID_TOKEN);

        String redisRefreshToken = redisService.getValue(user.getEmail());
        if (StringUtils.isEmpty(refreshToken) || StringUtils.isEmpty(redisRefreshToken) || !redisRefreshToken.equals(refreshToken)) {
            throw new GeneralException(ErrorStatus.INVALID_TOKEN);
        }

        return tokenProvider.reissue(user, refreshToken);
    }


}
