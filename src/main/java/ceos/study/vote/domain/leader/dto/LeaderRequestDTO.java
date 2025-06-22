package ceos.study.vote.domain.leader.dto;

import ceos.study.vote.global.common.PartType;
import lombok.Getter;

public class LeaderRequestDTO {
    @Getter
    public static class Vote{
        private Long id;
        private PartType part;
    }
}
