package com.interviewprep.service;

import com.interviewprep.dto.request.CreateQuizRequest;
import com.interviewprep.dto.response.QuizResponse;

public interface AdminService {
    QuizResponse createQuiz(CreateQuizRequest request);
    void deleteQuiz(Long id);
    QuizResponse updateQuiz(Long id, CreateQuizRequest request);
}
