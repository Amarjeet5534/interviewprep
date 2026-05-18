package com.interviewprep.service;

import com.interviewprep.dto.response.UserResponse;

public interface UserService {
    UserResponse getUserById(Long id);
    UserResponse getCurrentUser();
    UserResponse updateProfile(Long id, UserResponse userResponse);
}
