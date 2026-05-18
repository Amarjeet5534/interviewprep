package com.interviewprep.service.impl;

import com.interviewprep.dto.request.CreateQuizRequest;
import com.interviewprep.dto.response.QuizResponse;
import com.interviewprep.service.AdminService;
import com.interviewprep.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final QuizService quizService;

    @Override
    @Transactional
    public QuizResponse createQuiz(CreateQuizRequest request) {
        return quizService.createQuiz(request);
    }

    @Override
    @Transactional
    public void deleteQuiz(Long id) {
        quizService.deleteQuiz(id);
    }

    @Override
    @Transactional
    public QuizResponse updateQuiz(Long id, CreateQuizRequest request) {
        quizService.deleteQuiz(id);
        return quizService.createQuiz(request);
    }
}
