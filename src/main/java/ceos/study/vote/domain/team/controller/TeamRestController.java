package ceos.study.vote.domain.team.controller;

import ceos.study.vote.domain.team.dto.TeamRequestDto;
import ceos.study.vote.domain.team.dto.TeamResponseDto;
import ceos.study.vote.domain.team.service.TeamService;
import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.global.apiPayload.ApiResponse;
import ceos.study.vote.global.jwt.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "팀 투표 API", description = "팀 투표 관련 API")
@RestController
@RequiredArgsConstructor
public class TeamRestController {
    private final TeamService teamService;

    @PostMapping("votes/teams")
    @Operation(summary = "데모데이 투표")
    public ApiResponse<TeamResponseDto.VoteResultListDto> vote(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                               @RequestBody @Valid TeamRequestDto.VoteDto request) {
        User user = userDetails.getUser();
        return ApiResponse.onSuccess(teamService.vote(user, request));
    }
}
