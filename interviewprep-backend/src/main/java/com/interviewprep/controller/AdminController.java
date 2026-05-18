package com.interviewprep.controller;

import com.interviewprep.dto.request.CreateQuizRequest;
import com.interviewprep.dto.response.ApiResponse;
import com.interviewprep.dto.response.QuizResponse;
import com.interviewprep.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin endpoints")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/quizzes")
    @Operation(summary = "Create a new quiz")
    public ResponseEntity<ApiResponse<QuizResponse>> createQuiz(@Valid @RequestBody CreateQuizRequest request) {
        QuizResponse response = adminService.createQuiz(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Quiz created successfully", response));
    }

    @PutMapping("/quizzes/{id}")
    @Operation(summary = "Update a quiz")
    public ResponseEntity<ApiResponse<QuizResponse>> updateQuiz(@PathVariable Long id, 
                                                                @Valid @RequestBody CreateQuizRequest request) {
        QuizResponse response = adminService.updateQuiz(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz updated successfully", response));
    }

    @DeleteMapping("/quizzes/{id}")
    @Operation(summary = "Delete a quiz")
    public ResponseEntity<ApiResponse<?>> deleteQuiz(@PathVariable Long id) {
        adminService.deleteQuiz(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Quiz deleted successfully"));
    }
}
