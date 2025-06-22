package ceos.study.vote.domain.leader.dto;

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
    public static class VoteResult{
        private Long id;
        private String name;
        private Integer voteNum;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class VoteStats {
        private Long id;
        private String name;
        private String description;
        private Integer voteNum;
        private Double ratioVotes;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Info {
        private Long id;
        private String name;
        private String description;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class StatsList {
        private List<VoteStats> candidates;
        private Integer totalVotes;
    }
}
