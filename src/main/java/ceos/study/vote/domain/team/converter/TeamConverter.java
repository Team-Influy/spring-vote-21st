package ceos.study.vote.domain.team.converter;

import ceos.study.vote.domain.team.dto.TeamResponseDto;
import ceos.study.vote.domain.team.entity.Team;

import java.util.List;

public class TeamConverter {
    public static TeamResponseDto.VoteResultDto toVoteResultDto(Team team) {
        return TeamResponseDto.VoteResultDto.builder()
                .id(team.getId())
                .team(team.getTeam())
                .numVotes(team.getVoteNum())
                .build();
    }

    public static TeamResponseDto.VoteResultListDto toVoteResultListDto(List<Team> teamList, Integer totalVotes) {
        return TeamResponseDto.VoteResultListDto.builder()
                .resultList(teamList.stream().map(TeamConverter::toVoteResultDto).toList())
                .totalVotes(totalVotes)
                .build();
    }
}
