package com.interviewprep.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String profileImage;
    private String bio;
    private Integer totalScore;
    private Set<String> roles;
    private LocalDateTime createdAt;
}
