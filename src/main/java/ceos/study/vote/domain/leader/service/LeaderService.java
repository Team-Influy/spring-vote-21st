package ceos.study.vote.domain.leader.service;

import ceos.study.vote.domain.leader.dto.LeaderRequestDTO;
import ceos.study.vote.domain.leader.entity.Leader;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.global.common.PartType;

import java.util.List;

public interface LeaderService {
    Integer vote(LeaderRequestDTO.Vote request, User user);

    List<Leader> getCandidates(PartType part);
}
