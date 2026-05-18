package com.interviewprep.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leaderboard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private Integer totalScore;

    @Column(name = "`rank`", nullable = false)
    private Integer rank;

    @Column(nullable = false)
    private Integer attemptedQuizzes;

    @Column(nullable = false)
    private Double averageScore;
}
