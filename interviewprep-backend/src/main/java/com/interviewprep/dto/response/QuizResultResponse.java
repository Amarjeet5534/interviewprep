package com.interviewprep.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResultResponse {
    private Long quizAttemptId;
    private Integer score;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Double percentage;
    private String category;
    private String difficulty;
    private String quizTitle;
}
