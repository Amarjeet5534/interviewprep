package com.interviewprep.controller;

import com.interviewprep.dto.request.AIExplainRequest;
import com.interviewprep.dto.response.AIExplainResponse;
import com.interviewprep.dto.response.ApiResponse;
import com.interviewprep.service.AIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@Tag(name = "AI Explanations", description = "AI explanation endpoints")
@SecurityRequirement(name = "bearerAuth")
public class AIController {

    private final AIService aiService;

    @PostMapping("/explain")
    @Operation(summary = "Get AI explanation for an answer")
    public ResponseEntity<ApiResponse<AIExplainResponse>> explainAnswer(@Valid @RequestBody AIExplainRequest request) {
        AIExplainResponse response = aiService.explainAnswer(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Explanation generated successfully", response));
    }
}
