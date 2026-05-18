package com.interviewprep.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitQuizRequest {
    @NotNull(message = "Quiz ID is required")
    private Long quizId;

    @NotNull(message = "Answers are required")
    private Map<Long, Long> answers;
}
