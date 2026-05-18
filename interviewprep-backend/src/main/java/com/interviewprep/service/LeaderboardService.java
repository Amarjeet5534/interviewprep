package com.interviewprep.service;

import com.interviewprep.dto.response.LeaderboardEntryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeaderboardService {
    Page<LeaderboardEntryResponse> getLeaderboard(Pageable pageable);
    LeaderboardEntryResponse getUserRanking(Long userId);
    void updateLeaderboard(Long userId);
}
