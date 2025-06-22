package ceos.study.vote.domain.user.dto;

import ceos.study.vote.global.common.TeamType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpRequestDto {
        @NotEmpty
        @Schema(description = "이름", example = "나요나")
        private String name;

        @NotEmpty
        @Schema(description = "이메일", example = "influy@gmail.com")
        private String email;

        @NotEmpty
        @Schema(description = "비밀번호", example = "12345678")
        private String password;

        @NotEmpty
        @Schema(description = "팀 [INFLUY, HANIHOME, PROMESA, LOOPZ, DEARDREAM]", example = "INFLUY")
        private TeamType team;

        @NotEmpty
        @Schema(description = "파트", example = "백엔드")
        private String part;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInRequestDto {
        @NotEmpty
        @Schema(description = "이메일", example = "influy@gmail.com")
        private String email;

        @NotEmpty
        @Schema(description = "비밀번호", example = "12345678")
        private String password;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReissueRequestDto {
        @NotEmpty
        @Schema(description = "리프레시 토큰", example = "dssdsdfssfsfdfs")
        private String refreshToken;
    }
}
