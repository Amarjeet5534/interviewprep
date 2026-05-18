package com.interviewprep.service.impl;

import com.interviewprep.dto.request.AIExplainRequest;
import com.interviewprep.dto.response.AIExplainResponse;
import com.interviewprep.entity.Option;
import com.interviewprep.entity.Question;
import com.interviewprep.exception.ResourceNotFoundException;
import com.interviewprep.repository.OptionRepository;
import com.interviewprep.repository.QuestionRepository;
import com.interviewprep.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIServiceImpl implements AIService {

    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    @Override
    public AIExplainResponse explainAnswer(AIExplainRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        Option selectedOption = optionRepository.findById(request.getSelectedOptionId())
                .orElseThrow(() -> new ResourceNotFoundException("Option not found"));

        Option correctOption = question.getOptions().stream()
                .filter(Option::getIsCorrect)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Correct option not found"));

        String wrongAnswersExplanation = question.getOptions().stream()
                .filter(o -> !o.getIsCorrect())
                .map(o -> "- " + o.getOptionText() + ": This is incorrect because...")
                .collect(Collectors.joining("\n"));

        String response = generateAIExplanation(question, correctOption, selectedOption, wrongAnswersExplanation);

        return AIExplainResponse.builder()
                .questionId(question.getId())
                .questionText(question.getQuestionText())
                .correctAnswer(correctOption.getOptionText())
                .explanation(question.getExplanation() != null ? question.getExplanation() : 
                            "This is the correct answer because it best addresses the question requirements.")
                .whyWrong(selectedOption.getIsCorrect() ? "Your answer is correct!" : 
                         "This option was incorrect because: " + wrongAnswersExplanation)
                .interviewTips("Interview Tips:\n1. Understand the core concept\n2. Practice similar problems\n3. Explain your approach clearly")
                .followUpQuestion("Can you explain why this approach is better than the alternatives?")
                .build();
    }

    private String generateAIExplanation(Question question, Option correctOption, 
                                        Option selectedOption, String wrongAnswers) {
        return "Based on the question: " + question.getQuestionText() + "\n\n" +
               "The correct answer is: " + correctOption.getOptionText() + "\n\n" +
               "Explanation: " + question.getExplanation();
    }
}
