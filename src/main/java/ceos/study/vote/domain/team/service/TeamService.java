package ceos.study.vote.domain.team.service;

import ceos.study.vote.domain.team.dto.TeamRequestDto;
import ceos.study.vote.domain.team.dto.TeamResponseDto;
import ceos.study.vote.domain.user.entity.User;

import java.util.List;

public interface TeamService {
    TeamResponseDto.VoteResultDto vote(User user, TeamRequestDto.VoteDto request);
    TeamResponseDto.VoteStatusListDto status();
    List<TeamResponseDto.CandidateDto> candidates();
}
