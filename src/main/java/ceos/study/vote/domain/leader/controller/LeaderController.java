package ceos.study.vote.domain.leader.controller;


import ceos.study.vote.domain.leader.converter.LeaderConverter;
import ceos.study.vote.domain.leader.dto.LeaderRequestDTO;
import ceos.study.vote.domain.leader.dto.LeaderResponseDTO;
import ceos.study.vote.domain.leader.entity.Leader;
import ceos.study.vote.domain.leader.service.LeaderService;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.global.apiPayload.ApiResponse;
import ceos.study.vote.global.jwt.CustomUserDetails;
import ceos.study.vote.global.jwt.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LeaderController {

    private final LeaderService leaderService;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @PostMapping("votes/leaders")
    public ApiResponse<LeaderResponseDTO.GeneralList> vote(@RequestBody LeaderRequestDTO.Vote request,
                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = customUserDetailsService.findUserByUserDetails(userDetails);
        Integer totalVotes = leaderService.vote(request,user);
        List<Leader> candidates = leaderService.getCandidates(request.getPart());
        LeaderResponseDTO.GeneralList body = LeaderConverter.toGeneralListDTO(candidates, totalVotes);

        return ApiResponse.onSuccess(body);
    }


}
