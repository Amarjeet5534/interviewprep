package com.interviewprep.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIExplainRequest {
    @NotNull(message = "Question ID is required")
    private Long questionId;

    @NotNull(message = "Selected Option ID is required")
    private Long selectedOptionId;
}
