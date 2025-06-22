package ceos.study.vote.domain.leader.converter;

import ceos.study.vote.domain.leader.dto.LeaderResponseDTO;
import ceos.study.vote.domain.leader.entity.Leader;

import java.util.ArrayList;
import java.util.List;

public class LeaderConverter {

    //투표 완료 응답
    public static LeaderResponseDTO.VoteResult toVoteResult(Leader leader) {
        return LeaderResponseDTO.VoteResult.builder()
                .id(leader.getId())
                .name(leader.getName())
                .voteNum(leader.getVoteNum())
                .build();
    }

    //후보자 정보 조회
    public static LeaderResponseDTO.Info toInfoDTO(Leader leader) {
        return LeaderResponseDTO.Info.builder()
                .id(leader.getId())
                .name(leader.getName())
                .description(leader.getDescription())
                .build();
    }

    //투표 현황
    public static LeaderResponseDTO.VoteStats toStatsDTO(Leader leader, Integer totalVotes) {
        Double ratio = Math.round((double)leader.getVoteNum()/(double)totalVotes*10000)/100.0;
        return LeaderResponseDTO.VoteStats.builder()
                .id(leader.getId())
                .name(leader.getName())
                .description(leader.getDescription())
                .voteNum(leader.getVoteNum())
                .ratioVotes(ratio)
                .build();
    }

    public static LeaderResponseDTO.StatsList toStatsList(List<Leader> candidates, Integer totalVotes) {

        List<LeaderResponseDTO.VoteStats> voteStatsList = candidates.stream()
                .map(candidate->toStatsDTO(candidate,totalVotes)).toList();

        return LeaderResponseDTO.StatsList.builder()
                .candidates(voteStatsList)
                .totalVotes(totalVotes)
                .build();
    }
}
