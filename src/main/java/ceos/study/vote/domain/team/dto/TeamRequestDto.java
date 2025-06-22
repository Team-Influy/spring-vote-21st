package ceos.study.vote.domain.team.dto;

import ceos.study.vote.domain.team.entity.TeamType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TeamRequestDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteDto {
        @Schema(description = "투표하는 팀 이름 [INFLUY, HANIHOME, PROMESA, LOOPZ, DEARDREAM]", example = "INFLUY")
        private TeamType team;
    }
}
