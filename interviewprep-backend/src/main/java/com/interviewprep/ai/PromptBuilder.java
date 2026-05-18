package com.interviewprep.ai;

import com.interviewprep.entity.Option;
import com.interviewprep.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildExplanationPrompt(Question question, Option correctOption, Option selectedOption) {
        return String.format(
            "Question: %s\n\n" +
            "Correct Answer: %s\n\n" +
            "User Selected: %s\n\n" +
            "Please provide:\n" +
            "1. Why the correct answer is right\n" +
            "2. Why the selected answer is wrong\n" +
            "3. Interview tips related to this concept\n" +
            "4. A follow-up interview question\n\n" +
            "Format your response clearly with sections.",
            question.getQuestionText(),
            correctOption.getOptionText(),
            selectedOption.getOptionText()
        );
    }

    public String buildComprehensiveExplanation(Question question, Option correctOption) {
        return String.format(
            "Explain this technical concept for interview preparation:\n\n" +
            "Question: %s\n" +
            "Correct Answer: %s\n\n" +
            "Provide a comprehensive explanation including:\n" +
            "1. Core concept explanation\n" +
            "2. Real-world applications\n" +
            "3. Time and space complexity (if applicable)\n" +
            "4. Interview tips\n" +
            "5. Common follow-up questions",
            question.getQuestionText(),
            correctOption.getOptionText()
        );
    }
}
