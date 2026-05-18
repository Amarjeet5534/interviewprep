package com.interviewprep.service;

import com.interviewprep.dto.request.CreateQuizRequest;
import com.interviewprep.dto.request.SubmitQuizRequest;
import com.interviewprep.dto.response.QuizResultResponse;
import com.interviewprep.dto.response.QuizResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {
    List<QuizResponse> getAllQuizzes();
    Page<QuizResponse> getQuizzesByCategory(String category, Pageable pageable);
    Page<QuizResponse> getQuizzesByDifficulty(String difficulty, Pageable pageable);
    QuizResponse getQuizById(Long id);
    QuizResponse createQuiz(CreateQuizRequest request);
    QuizResultResponse submitQuiz(SubmitQuizRequest request);
    void deleteQuiz(Long id);
}
