package com.interviewprep.repository;

import com.interviewprep.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCategory(String category);
    List<Quiz> findByDifficulty(String difficulty);
    Page<Quiz> findByCategory(String category, Pageable pageable);
    Page<Quiz> findByDifficulty(String difficulty, Pageable pageable);
    List<Quiz> findByCategoryAndDifficulty(String category, String difficulty);
}
