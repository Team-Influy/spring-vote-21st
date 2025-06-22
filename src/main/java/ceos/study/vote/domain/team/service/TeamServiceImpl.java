package ceos.study.vote.domain.team.service;

import ceos.study.vote.domain.team.converter.TeamConverter;
import ceos.study.vote.domain.team.dto.TeamRequestDto;
import ceos.study.vote.domain.team.dto.TeamResponseDto;
import ceos.study.vote.domain.team.entity.Team;
import ceos.study.vote.domain.team.repository.TeamRepository;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.domain.user.repository.UserRepository;
import ceos.study.vote.global.apiPayload.code.status.ErrorStatus;
import ceos.study.vote.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TeamResponseDto.VoteResultListDto vote(User user, TeamRequestDto.VoteDto request) {
        Team team = teamRepository.findByTeam(request.getTeam())
                .orElseThrow(() -> new GeneralException(ErrorStatus.TEAM_NOT_FOUND));

        // 본인 팀에게 투표 못함
        if (user.getTeam().equals(team.getTeam().toString())) throw new GeneralException(ErrorStatus.NOT_VALID_VOTE);

        // 아직 투표하지 않은 유저만 투표 가능
        if (!user.getTeamVote()) {
            team.addVote();
            user.setTeamVoteTrue();
            userRepository.save(user);
        } else {
            throw new GeneralException(ErrorStatus.ALREADY_VOTED);
        }

        return TeamConverter.toVoteResultListDto(teamRepository.findAll().stream().toList(), teamRepository.getTotalVotes());
    }
}
