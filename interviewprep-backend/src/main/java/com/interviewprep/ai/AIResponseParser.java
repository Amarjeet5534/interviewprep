package com.interviewprep.ai;

import org.springframework.stereotype.Component;

@Component
public class AIResponseParser {
    
    public String extractMainResponse(String response) {
        if (response == null || response.isEmpty()) {
            return "";
        }
        
        // Remove markdown formatting
        String cleaned = response
            .replaceAll("\\*\\*", "")
            .replaceAll("##\\s+", "")
            .replaceAll("\\n\\n+", "\n");
        
        return cleaned.trim();
    }

    public String extractSection(String response, String sectionName) {
        if (response == null || response.isEmpty()) {
            return "";
        }
        
        String[] lines = response.split("\n");
        StringBuilder section = new StringBuilder();
        boolean inSection = false;
        
        for (String line : lines) {
            if (line.toLowerCase().contains(sectionName.toLowerCase())) {
                inSection = true;
                continue;
            }
            
            if (inSection) {
                if (line.startsWith("##") || line.startsWith("###")) {
                    break;
                }
                section.append(line).append("\n");
            }
        }
        
        return section.toString().trim();
    }
}
