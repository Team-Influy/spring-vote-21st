package ceos.study.vote.domain.team.dto;

import ceos.study.vote.domain.team.entity.TeamType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class TeamResponseDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteResultDto {
        @Schema(description = "팀 id", example = "1")
        private Long id;

        @Schema(description = "팀 이름", example = "INFLUY")
        private TeamType team;

        @Schema(description = "득표수", example = "7")
        private Integer numVotes;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteResultListDto {
        @Schema(description = "팀별 투표 결과 리스트")
        private List<VoteResultDto> resultList;

        @Schema(description = "총 투표수", example = "20")
        private Integer totalVotes;
    }

}
