package com.interviewprep.repository;

import com.interviewprep.entity.Leaderboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    Optional<Leaderboard> findByUserId(Long userId);
    Page<Leaderboard> findAllByOrderByRankAsc(Pageable pageable);
    Page<Leaderboard> findAllByOrderByTotalScoreDesc(Pageable pageable);
}
