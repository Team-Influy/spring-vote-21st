package ceos.study.vote.domain.leader.service;


import ceos.study.vote.domain.leader.dto.LeaderRequestDTO;
import ceos.study.vote.domain.leader.entity.Leader;
import ceos.study.vote.domain.leader.repository.LeaderRepository;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.domain.user.repository.UserRepository;
import ceos.study.vote.global.apiPayload.code.status.ErrorStatus;
import ceos.study.vote.global.apiPayload.exception.GeneralException;
import ceos.study.vote.global.common.PartType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeaderServiceImpl implements LeaderService {

    private final LeaderRepository leaderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Leader vote(LeaderRequestDTO.Vote request, User user) {
        if(user.getLeaderVote()){
            throw new GeneralException(ErrorStatus.ALREADY_VOTED);
        }

        Leader candidate = leaderRepository.findById(request.getId())
                .orElseThrow(()->new GeneralException(ErrorStatus.LEADER_NOT_FOUND));

        if(!(user.getPart().equals(candidate.getPart()))) {
            throw new GeneralException(ErrorStatus.WRONG_PART);
        }

        candidate.addVote();
        user.setLeaderVoteTrue();

        return candidate;
    }

    @Override
    public List<Leader> getCandidates(PartType part) {
        return leaderRepository.findAllByPart(part);
    }

    @Override
    public Integer getTotalVotes(PartType part) {
        return userRepository.countUserByLeaderVoteTrueAndPart(part);
    }


}
