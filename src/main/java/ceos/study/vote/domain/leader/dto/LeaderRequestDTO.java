package ceos.study.vote.domain.leader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public class LeaderRequestDTO {
    @Getter
    public static class Vote{
        @Schema(description = "투표하는 후보자 id", example = "1")
        private Long id;
    }
}
