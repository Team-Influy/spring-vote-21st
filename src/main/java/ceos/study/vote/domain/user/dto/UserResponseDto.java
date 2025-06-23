package ceos.study.vote.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultDto {
        @Schema(description = "유저 id", example = "1")
        private Long id;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInResultDto {
        @Schema(description = "유저 id", example = "1")
        private Long id;

        @Schema(description = "유저 이름", example = "나야나")
        private String name;

        @Schema(description = "엑세스 토큰", example = "fsfdfsfsdfdsfsfs")
        private String accessToken;

        @Schema(description = "리프레시 토큰", example = "dsfsfsfsfsfsfsfsf")
        private String refreshToken;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReissueResultDto {
        @Schema(description = "유저 id", example = "1")
        private Long id;

        @Schema(description = "엑세스 토큰", example = "fsfdfsfsdfdsfsfs")
        private String accessToken;

        @Schema(description = "리프레시 토큰", example = "dsfsfsfsfsfsfsfsf")
        private String refreshToken;
    }
}
