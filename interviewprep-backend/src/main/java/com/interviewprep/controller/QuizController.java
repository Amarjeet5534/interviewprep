package com.interviewprep.controller;

import com.interviewprep.dto.request.SubmitQuizRequest;
import com.interviewprep.dto.response.ApiResponse;
import com.interviewprep.dto.response.QuizResponse;
import com.interviewprep.dto.response.QuizResultResponse;
import com.interviewprep.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
@Tag(name = "Quizzes", description = "Quiz endpoints")
public class QuizController {

    private final QuizService quizService;

    @GetMapping
    @Operation(summary = "Get all quizzes")
    public ResponseEntity<ApiResponse<List<QuizResponse>>> getAllQuizzes() {
        List<QuizResponse> response = quizService.getAllQuizzes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Quizzes retrieved successfully", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get quiz by ID")
    public ResponseEntity<ApiResponse<QuizResponse>> getQuizById(@PathVariable Long id) {
        QuizResponse response = quizService.getQuizById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz retrieved successfully", response));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get quizzes by category")
    public ResponseEntity<ApiResponse<Page<QuizResponse>>> getQuizzesByCategory(
            @PathVariable String category, Pageable pageable) {
        Page<QuizResponse> response = quizService.getQuizzesByCategory(category, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quizzes retrieved successfully", response));
    }

    @GetMapping("/difficulty/{difficulty}")
    @Operation(summary = "Get quizzes by difficulty")
    public ResponseEntity<ApiResponse<Page<QuizResponse>>> getQuizzesByDifficulty(
            @PathVariable String difficulty, Pageable pageable) {
        Page<QuizResponse> response = quizService.getQuizzesByDifficulty(difficulty, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quizzes retrieved successfully", response));
    }

    @PostMapping("/submit")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Submit quiz answers")
    public ResponseEntity<ApiResponse<QuizResultResponse>> submitQuiz(@RequestBody SubmitQuizRequest request) {
        QuizResultResponse response = quizService.submitQuiz(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz submitted successfully", response));
    }
}
