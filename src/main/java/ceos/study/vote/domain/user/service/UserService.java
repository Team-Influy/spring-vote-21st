package ceos.study.vote.domain.user.service;

import ceos.study.vote.domain.user.dto.UserRequestDto;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.global.jwt.TokenProvider;

public interface UserService {
    User signUp(UserRequestDto.SignUpRequestDto request);
    TokenProvider.TokenPair signIn(UserRequestDto.SignInRequestDto request);
    void signOut(String accessToken, User user);
    TokenProvider.TokenPair reissue(UserRequestDto.ReissueRequestDto request, User user);
}
