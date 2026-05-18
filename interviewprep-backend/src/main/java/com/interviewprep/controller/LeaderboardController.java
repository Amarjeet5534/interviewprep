package com.interviewprep.controller;

import com.interviewprep.dto.response.ApiResponse;
import com.interviewprep.dto.response.LeaderboardEntryResponse;
import com.interviewprep.service.LeaderboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
@Tag(name = "Leaderboard", description = "Leaderboard endpoints")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping
    @Operation(summary = "Get leaderboard")
    public ResponseEntity<ApiResponse<Page<LeaderboardEntryResponse>>> getLeaderboard(Pageable pageable) {
        Page<LeaderboardEntryResponse> response = leaderboardService.getLeaderboard(pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Leaderboard retrieved successfully", response));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user ranking")
    public ResponseEntity<ApiResponse<LeaderboardEntryResponse>> getUserRanking(@PathVariable Long userId) {
        LeaderboardEntryResponse response = leaderboardService.getUserRanking(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User ranking retrieved successfully", response));
    }
}
