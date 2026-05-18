package com.interviewprep.service.impl;

import com.interviewprep.dto.response.LeaderboardEntryResponse;
import com.interviewprep.dto.response.UserResponse;
import com.interviewprep.entity.Leaderboard;
import com.interviewprep.entity.QuizAttempt;
import com.interviewprep.entity.Role;
import com.interviewprep.entity.User;
import com.interviewprep.exception.ResourceNotFoundException;
import com.interviewprep.repository.LeaderboardRepository;
import com.interviewprep.repository.QuizAttemptRepository;
import com.interviewprep.repository.UserRepository;
import com.interviewprep.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final UserRepository userRepository;
    private final QuizAttemptRepository quizAttemptRepository;

    @Override
    public Page<LeaderboardEntryResponse> getLeaderboard(Pageable pageable) {
        List<LeaderboardEntryResponse> leaderboard = buildLeaderboardSnapshot();
        int start = (int) Math.min(pageable.getOffset(), leaderboard.size());
        int end = Math.min(start + pageable.getPageSize(), leaderboard.size());
        List<LeaderboardEntryResponse> pageContent = leaderboard.subList(start, end);
        return new PageImpl<>(pageContent, pageable, leaderboard.size());
    }

    @Override
    public LeaderboardEntryResponse getUserRanking(Long userId) {
        return buildLeaderboardSnapshot().stream()
            .filter(entry -> entry.getUser().getId().equals(userId))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException("User ranking not found"));
    }

    @Override
    @Transactional
    public void updateLeaderboard(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<QuizAttempt> attempts = quizAttemptRepository.findByUserId(userId);
        
        if (attempts.isEmpty()) {
            return;
        }

        int totalScore = attempts.stream().mapToInt(QuizAttempt::getScore).sum();
        double averageScore = (double) totalScore / attempts.size();

        Leaderboard leaderboard = leaderboardRepository.findByUserId(userId)
                .orElse(Leaderboard.builder()
                        .user(user)
                        .totalScore(0)
                        .rank(0)
                        .attemptedQuizzes(0)
                        .averageScore(0.0)
                        .build());

        leaderboard.setTotalScore(totalScore);
        leaderboard.setAttemptedQuizzes(attempts.size());
        leaderboard.setAverageScore(averageScore);

        leaderboardRepository.save(leaderboard);
        updateRanks();
    }

    private void updateRanks() {
        List<Leaderboard> leaderboards = leaderboardRepository.findAll().stream()
                .sorted((a, b) -> Integer.compare(b.getTotalScore(), a.getTotalScore()))
                .collect(Collectors.toList());

        for (int i = 0; i < leaderboards.size(); i++) {
            leaderboards.get(i).setRank(i + 1);
            leaderboardRepository.save(leaderboards.get(i));
        }
    }

        private List<LeaderboardEntryResponse> buildLeaderboardSnapshot() {
        List<User> users = userRepository.findAll();
        List<User> sortedUsers = users.stream()
            .sorted(Comparator.comparingInt((User user) -> user.getTotalScore() != null ? user.getTotalScore() : 0)
                .reversed()
                .thenComparing(User::getName))
            .toList();

        List<LeaderboardEntryResponse> snapshot = new ArrayList<>();
        for (int i = 0; i < sortedUsers.size(); i++) {
            User user = sortedUsers.get(i);
            List<QuizAttempt> attempts = quizAttemptRepository.findByUserId(user.getId());
            int totalScore = user.getTotalScore() != null ? user.getTotalScore() : 0;
            double averageScore = attempts.isEmpty() ? 0.0 : (double) totalScore / attempts.size();

            UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .roles(user.getRoles().stream()
                    .map(Role::getRoleName)
                    .collect(Collectors.toSet()))
                .build();

            snapshot.add(LeaderboardEntryResponse.builder()
                .rank(i + 1)
                .user(userResponse)
                .totalScore(totalScore)
                .attemptedQuizzes(attempts.size())
                .averageScore(averageScore)
                .build());
        }

        return snapshot;
        }

    private LeaderboardEntryResponse mapToLeaderboardEntryResponse(Leaderboard leaderboard) {
        User user = leaderboard.getUser();
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .roles(user.getRoles().stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toSet()))
                .build();

        return LeaderboardEntryResponse.builder()
                .rank(leaderboard.getRank())
                .user(userResponse)
                .totalScore(leaderboard.getTotalScore())
                .attemptedQuizzes(leaderboard.getAttemptedQuizzes())
                .averageScore(leaderboard.getAverageScore())
                .build();
    }
}
