package ceos.study.vote.domain.team.dto;

import ceos.study.vote.global.common.TeamType;
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
        @Schema(description = "유저 id", example = "1")
        private Long id;

        @Schema(description = "투표한 팀 id", example = "2")
        private Long teamId;

        @Schema(description = "투표한 팀 이름", example = "INFLUY")
        private TeamType teamType;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteStatusDto {
        @Schema(description = "팀 id", example = "1")
        private Long id;

        @Schema(description = "팀 이름", example = "INFLUY")
        private TeamType team;

        @Schema(description = "팀 설명", example = "옹냥냥")
        private String description;

        @Schema(description = "득표수", example = "7")
        private Integer numVotes;

        @Schema(description = "득표 퍼센트 (해당 팀 득표수 / 전체 투표수)", example = "15.0")
        private Double ratioVotes;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteStatusListDto {
        @Schema(description = "팀별 투표 결과 리스트")
        private List<VoteStatusDto> resultList;

        @Schema(description = "총 투표수", example = "20")
        private Integer totalVotes;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CandidateDto {
        @Schema(description = "팀 id", example = "1")
        private Long id;

        @Schema(description = "팀 이름", example = "INFLUY")
        private TeamType team;

        @Schema(description = "팀 설명", example = "옹냥냥")
        private String description;
    }
}
