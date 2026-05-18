package com.interviewprep.service.impl;

import com.interviewprep.dto.request.CreateQuizRequest;
import com.interviewprep.dto.request.SubmitQuizRequest;
import com.interviewprep.dto.response.QuizResultResponse;
import com.interviewprep.dto.response.QuizResponse;
import com.interviewprep.dto.response.UserResponse;
import com.interviewprep.entity.*;
import com.interviewprep.exception.ResourceNotFoundException;
import com.interviewprep.repository.*;
import com.interviewprep.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final UserRepository userRepository;

    @Override
    public List<QuizResponse> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(this::mapToQuizResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<QuizResponse> getQuizzesByCategory(String category, Pageable pageable) {
        return quizRepository.findByCategory(category, pageable)
                .map(this::mapToQuizResponse);
    }

    @Override
    public Page<QuizResponse> getQuizzesByDifficulty(String difficulty, Pageable pageable) {
        return quizRepository.findByDifficulty(difficulty, pageable)
                .map(this::mapToQuizResponse);
    }

    @Override
    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
        return mapToQuizResponse(quiz);
    }

    @Override
    @Transactional
    public QuizResponse createQuiz(CreateQuizRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User creator = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Quiz quiz = Quiz.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .difficulty(request.getDifficulty())
                .duration(request.getDuration())
                .createdBy(creator)
                .build();

        Quiz savedQuiz = quizRepository.save(quiz);

        if (request.getQuestions() != null) {
            for (CreateQuizRequest.QuestionRequest qReq : request.getQuestions()) {
                Question question = Question.builder()
                        .quiz(savedQuiz)
                        .questionText(qReq.getQuestionText())
                        .explanation(qReq.getExplanation())
                        .difficulty(qReq.getDifficulty())
                        .build();

                Question savedQuestion = questionRepository.save(question);

                if (qReq.getOptions() != null) {
                    for (CreateQuizRequest.QuestionRequest.OptionRequest oReq : qReq.getOptions()) {
                        Option option = Option.builder()
                                .question(savedQuestion)
                                .optionText(oReq.getOptionText())
                                .isCorrect(oReq.getIsCorrect())
                                .build();
                        optionRepository.save(option);
                    }
                }
            }
        }

        return getQuizById(savedQuiz.getId());
    }

    @Override
    @Transactional
    public QuizResultResponse submitQuiz(SubmitQuizRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        List<Question> questions = questionRepository.findByQuizId(request.getQuizId());
        int correctCount = 0;

        for (Question question : questions) {
            Long selectedOptionId = request.getAnswers().get(question.getId());
            if (selectedOptionId != null) {
                Option selectedOption = optionRepository.findById(selectedOptionId)
                        .orElseThrow(() -> new ResourceNotFoundException("Option not found"));
                if (selectedOption.getIsCorrect()) {
                    correctCount++;
                }
            }
        }

        double percentage = (double) correctCount / questions.size() * 100;
        int score = (int) percentage;

        QuizAttempt attempt = QuizAttempt.builder()
                .user(user)
                .quiz(quiz)
                .score(score)
                .totalQuestions(questions.size())
                .correctAnswers(correctCount)
                .completedAt(LocalDateTime.now())
                .build();

        quizAttemptRepository.save(attempt);

        user.setTotalScore((user.getTotalScore() != null ? user.getTotalScore() : 0) + score);
        userRepository.save(user);

        return QuizResultResponse.builder()
                .quizAttemptId(attempt.getId())
                .score(score)
                .totalQuestions(questions.size())
                .correctAnswers(correctCount)
                .percentage(percentage)
                .category(quiz.getCategory())
                .difficulty(quiz.getDifficulty())
                .quizTitle(quiz.getTitle())
                .build();
    }

    @Override
    @Transactional
    public void deleteQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
        quizRepository.delete(quiz);
    }

    private QuizResponse mapToQuizResponse(Quiz quiz) {
        List<QuizResponse.QuestionResponse> questionResponses = quiz.getQuestions().stream()
                .map(q -> QuizResponse.QuestionResponse.builder()
                        .id(q.getId())
                        .questionText(q.getQuestionText())
                        .difficulty(q.getDifficulty())
                        .options(q.getOptions().stream()
                                .map(o -> QuizResponse.QuestionResponse.OptionResponse.builder()
                                        .id(o.getId())
                                        .optionText(o.getOptionText())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        UserResponse creatorResponse = UserResponse.builder()
                .id(quiz.getCreatedBy().getId())
                .name(quiz.getCreatedBy().getName())
                .email(quiz.getCreatedBy().getEmail())
                .build();

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .category(quiz.getCategory())
                .difficulty(quiz.getDifficulty())
                .duration(quiz.getDuration())
                .createdBy(creatorResponse)
                .totalQuestions(quiz.getQuestions().size())
                .createdAt(quiz.getCreatedAt())
                .questions(questionResponses)
                .build();
    }
}
