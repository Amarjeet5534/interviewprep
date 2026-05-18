package com.interviewprep.service;

import com.interviewprep.dto.request.AIExplainRequest;
import com.interviewprep.dto.response.AIExplainResponse;

public interface AIService {
    AIExplainResponse explainAnswer(AIExplainRequest request);
}
