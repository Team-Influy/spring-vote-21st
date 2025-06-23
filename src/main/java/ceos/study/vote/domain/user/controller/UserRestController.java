package ceos.study.vote.domain.user.controller;

import ceos.study.vote.domain.user.converter.UserConverter;
import ceos.study.vote.domain.user.dto.UserRequestDto;
import ceos.study.vote.domain.user.dto.UserResponseDto;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.domain.user.service.UserService;
import ceos.study.vote.global.apiPayload.ApiResponse;
import ceos.study.vote.global.apiPayload.code.status.SuccessStatus;
import ceos.study.vote.global.jwt.CustomUserDetails;
import ceos.study.vote.global.jwt.JwtAuthenticationFilter;
import ceos.study.vote.global.jwt.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저 로그인", description = "유저 로그인 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @PostMapping("/sign-up")
    @Operation(summary = "일반 회원가입 API")
    public ApiResponse<UserResponseDto.ResultDto> signUp(@RequestBody @Valid UserRequestDto.SignUpRequestDto request) {
        User user = userService.signUp(request);
        return ApiResponse.onSuccess(UserConverter.toResultDto(user));
    }

    @PostMapping("/sign-in")
    @Operation(summary = "일반 로그인 API")
    public ApiResponse<UserResponseDto.SignInResultDto> signIn(@RequestBody @Valid UserRequestDto.SignInRequestDto request) {
        TokenProvider.TokenPair tokenPair = userService.signIn(request);
        return ApiResponse.onSuccess(UserConverter.toSignInResultDto(tokenPair));
    }

    @PostMapping("/sign-out")
    @Operation(summary = "일반 로그아웃 API")
    public ApiResponse<UserResponseDto.ResultDto> signOut(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        userService.signOut(jwtAuthenticationFilter.resolveToken(request), customUserDetails.getUser());
        return ApiResponse.onSuccess(SuccessStatus.COMPLETE_LOGOUT);
    }

    @PostMapping("/reissue")
    @Operation(summary = "리프레시 토큰으로 액세스 토큰 재발급 API")
    public ApiResponse<UserResponseDto.ReissueResultDto> reissue(@RequestBody @Valid UserRequestDto.ReissueRequestDto request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        TokenProvider.TokenPair tokenPair = userService.reissue(request, customUserDetails.getUser());
        return ApiResponse.onSuccess(UserConverter.toReissueResultDto(tokenPair));
    }
}
