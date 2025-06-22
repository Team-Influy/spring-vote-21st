package ceos.study.vote.domain.user.converter;

import ceos.study.vote.domain.user.dto.UserRequestDto;
import ceos.study.vote.domain.user.dto.UserResponseDto;
import ceos.study.vote.domain.user.entity.RoleType;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.global.jwt.TokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserConverter {
    public static User toUser(UserRequestDto.SignUpRequestDto request, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .team(request.getTeam())
                .part(request.getPart())
                .roleType(RoleType.USER)
                .leaderVote(false)
                .teamVote(false)
                .build();
    }

    public static UserResponseDto.ResultDto toResultDto(User user) {
        return UserResponseDto.ResultDto.builder()
                .id(user.getId())
                .build();
    }

    public static UserResponseDto.SignInResultDto toSignInResultDto(TokenProvider.TokenPair tokenPair) {
        return UserResponseDto.SignInResultDto.builder()
                .id(tokenPair.user().getId())
                .name(tokenPair.user().getName())
                .accessToken(tokenPair.accessToken())
                .refreshToken(tokenPair.refreshToken())
                .build();
    }

    public static UserResponseDto.ReissueResultDto toReissueResultDto(TokenProvider.TokenPair tokenPair) {
        return UserResponseDto.ReissueResultDto.builder()
                .id(tokenPair.user().getId())
                .accessToken(tokenPair.accessToken())
                .refreshToken(tokenPair.refreshToken())
                .build();
    }
}
