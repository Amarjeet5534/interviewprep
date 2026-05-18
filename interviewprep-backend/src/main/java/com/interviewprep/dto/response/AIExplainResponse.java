package com.interviewprep.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIExplainResponse {
    private Long questionId;
    private String questionText;
    private String correctAnswer;
    private String explanation;
    private String whyWrong;
    private String interviewTips;
    private String followUpQuestion;
}
