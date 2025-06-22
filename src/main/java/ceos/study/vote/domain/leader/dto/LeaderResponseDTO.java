package ceos.study.vote.domain.leader.dto;

import ceos.study.vote.global.common.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class LeaderResponseDTO {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Details{
        private Long id;
        private String name;
        private String description;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class General{
        private Long id;
        private String name;
        private Integer voteNum;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class GeneralList{
        private List<General> candidates;
        private Integer totalVotes;
    }
}
