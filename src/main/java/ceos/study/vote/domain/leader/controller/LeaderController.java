package ceos.study.vote.domain.leader.controller;


import ceos.study.vote.domain.leader.converter.LeaderConverter;
import ceos.study.vote.domain.leader.dto.LeaderRequestDTO;
import ceos.study.vote.domain.leader.dto.LeaderResponseDTO;
import ceos.study.vote.domain.leader.entity.Leader;
import ceos.study.vote.domain.leader.service.LeaderService;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.global.apiPayload.ApiResponse;
import ceos.study.vote.global.common.PartType;
import ceos.study.vote.global.jwt.CustomUserDetails;
import ceos.study.vote.global.jwt.CustomUserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="파트장 투표 API")
public class LeaderController {

    private final LeaderService leaderService;
    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @PostMapping("votes/leaders")
    @Operation(summary = "파트장 투표")
    public ApiResponse<LeaderResponseDTO.VoteResult> vote(@RequestBody LeaderRequestDTO.Vote request,
                                                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = customUserDetailsService.findUserByUserDetails(userDetails);
        Leader leader = leaderService.vote(request,user);
        LeaderResponseDTO.VoteResult body = LeaderConverter.toVoteResult(leader);

        return ApiResponse.onSuccess(body);
    }

    //후보자 정보 조회
    @GetMapping("candidates/leaders/{part}")
    @Operation(summary = "파트장 후보 리스트 조회")
    public ApiResponse<List<LeaderResponseDTO.Info>> details(@PathVariable("part") PartType part) {

        List<Leader> candidates = leaderService.getCandidates(part);
        List<LeaderResponseDTO.Info> body = candidates.stream().map(LeaderConverter::toInfoDTO).toList();

        return ApiResponse.onSuccess(body);
    }

    //투표 현황
    @GetMapping("votes/leaders/{part}/status")
    @Operation(summary = "파트장 투표 현황 조회")
    public ApiResponse<LeaderResponseDTO.StatsList> voteStats(@PathVariable("part") PartType part) {

        List<Leader> candidates = leaderService.getCandidatesOrderByVoteNum(part);
        Integer totalVotes = leaderService.getTotalVotes(part);
        LeaderResponseDTO.StatsList body = LeaderConverter.toStatsList(candidates,totalVotes);

        return ApiResponse.onSuccess(body);
    }


}
