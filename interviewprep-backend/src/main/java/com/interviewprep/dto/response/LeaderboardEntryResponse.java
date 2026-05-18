package com.interviewprep.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaderboardEntryResponse {
    private Integer rank;
    private UserResponse user;
    private Integer totalScore;
    private Integer attemptedQuizzes;
    private Double averageScore;
}
