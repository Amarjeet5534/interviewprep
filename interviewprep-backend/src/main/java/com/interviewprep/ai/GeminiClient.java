package com.interviewprep.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GeminiClient {

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.model:gemini-pro}")
    private String model;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateExplanation(String prompt) {
        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;
            
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            
            // Build the request body
            String requestBody = buildRequestBody(prompt);
            httpPost.setEntity(new StringEntity(requestBody));
            
            // Execute the request
            String response = httpClient.execute(httpPost, response1 -> {
                String content = EntityUtils.toString(response1.getEntity());
                if (response1.getCode() == 200) {
                    return content;
                } else {
                    log.error("Gemini API error: HTTP {}", response1.getCode());
                    return null;
                }
            });
            
            if (response != null) {
                return parseResponse(response);
            } else {
                return "Unable to generate explanation at the moment. Please try again later.";
            }
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return "An unexpected error occurred. Please try again.";
        }
    }

    private String buildRequestBody(String prompt) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = """
                {
                    "contents": [
                        {
                            "parts": [
                                {
                                    "text": "%s"
                                }
                            ]
                        }
                    ]
                }
                """.formatted(escape(prompt));
        return json;
    }

    private String parseResponse(String response) throws Exception {
        JsonNode node = objectMapper.readTree(response);
        if (node.has("candidates") && node.get("candidates").isArray() && node.get("candidates").size() > 0) {
            JsonNode firstCandidate = node.get("candidates").get(0);
            if (firstCandidate.has("content") && firstCandidate.get("content").has("parts")) {
                JsonNode parts = firstCandidate.get("content").get("parts");
                if (parts.isArray() && parts.size() > 0) {
                    String text = parts.get(0).get("text").asText();
                    return text;
                }
            }
        }
        return "Unable to parse response from Gemini API.";
    }

    private String escape(String input) {
        return input.replace("\"", "\\\"").replace("\n", "\\n");
    }
}
