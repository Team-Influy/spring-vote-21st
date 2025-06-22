package ceos.study.vote.domain.leader.converter;

import ceos.study.vote.domain.leader.dto.LeaderResponseDTO;
import ceos.study.vote.domain.leader.entity.Leader;

import java.util.List;

public class LeaderConverter {

    public static LeaderResponseDTO.General toGeneralDTO(Leader leader) {
        return LeaderResponseDTO.General.builder()
                .id(leader.getId())
                .name(leader.getName())
                .voteNum(leader.getVoteNum())
                .build();
    }

    public static LeaderResponseDTO.GeneralList toGeneralListDTO(List<Leader> candidates, Integer totalVotes) {
        return LeaderResponseDTO.GeneralList.builder()
                .candidates(candidates.stream().map(LeaderConverter::toGeneralDTO).toList())
                .totalVotes(totalVotes)
                .build();
    }
}
