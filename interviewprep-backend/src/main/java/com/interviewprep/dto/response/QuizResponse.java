package com.interviewprep.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String difficulty;
    private Integer duration;
    private UserResponse createdBy;
    private Integer totalQuestions;
    private LocalDateTime createdAt;
    private List<QuestionResponse> questions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionResponse {
        private Long id;
        private String questionText;
        private String difficulty;
        private List<OptionResponse> options;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class OptionResponse {
            private Long id;
            private String optionText;
        }
    }
}
