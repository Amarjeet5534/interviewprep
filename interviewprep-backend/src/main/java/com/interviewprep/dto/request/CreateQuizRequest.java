package com.interviewprep.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateQuizRequest {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Difficulty is required")
    private String difficulty;

    @NotNull(message = "Duration is required")
    private Integer duration;

    @NotNull(message = "Questions are required")
    private List<QuestionRequest> questions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionRequest {
        @NotBlank(message = "Question text is required")
        private String questionText;

        private String explanation;

        @NotBlank(message = "Difficulty is required")
        private String difficulty;

        @NotNull(message = "Options are required")
        private List<OptionRequest> options;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class OptionRequest {
            @NotBlank(message = "Option text is required")
            private String optionText;

            @NotNull(message = "isCorrect is required")
            private Boolean isCorrect;
        }
    }
}
