package ceos.study.vote.domain.team.converter;

import ceos.study.vote.domain.team.dto.TeamResponseDto;
import ceos.study.vote.domain.team.entity.Team;
import ceos.study.vote.domain.user.entity.User;

import java.util.List;

public class TeamConverter {
    public static TeamResponseDto.VoteResultDto toVoteResultDto(User user, Team team) {
        return TeamResponseDto.VoteResultDto.builder()
                .id(user.getId())
                .teamId(team.getId())
                .teamType(team.getTeam())
                .build();
    }

    public static TeamResponseDto.VoteStatusDto toVoteStatusDto(Team team, Integer totalVotes) {
        return TeamResponseDto.VoteStatusDto.builder()
                .id(team.getId())
                .team(team.getTeam())
                .description(team.getDescription())
                .numVotes(team.getVoteNum())
                .ratioVotes(Math.round(((double) team.getVoteNum() / totalVotes) * 10000) / 100.0)
                .build();
    }

    public static TeamResponseDto.VoteStatusListDto toVoteStatusListDto(List<Team> teamList, Integer totalVotes) {
        return TeamResponseDto.VoteStatusListDto.builder()
                .resultList(teamList.stream().map(team -> TeamConverter.toVoteStatusDto(team, totalVotes)).toList())
                .totalVotes(totalVotes)
                .build();
    }

    public static List<TeamResponseDto.CandidateDto> toCandidateDtoList(List<Team> teamList) {
        return teamList.stream()
                .map(team -> TeamResponseDto.CandidateDto.builder()
                        .id(team.getId())
                        .team(team.getTeam())
                        .description(team.getDescription())
                        .build())
                .toList();
    }
}
