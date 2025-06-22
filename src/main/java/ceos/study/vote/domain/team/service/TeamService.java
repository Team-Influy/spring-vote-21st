package ceos.study.vote.domain.team.service;

import ceos.study.vote.domain.team.dto.TeamRequestDto;
import ceos.study.vote.domain.team.dto.TeamResponseDto;
import ceos.study.vote.domain.user.entity.User;

public interface TeamService {
    TeamResponseDto.VoteResultListDto vote(User user, TeamRequestDto.VoteDto request);
}
