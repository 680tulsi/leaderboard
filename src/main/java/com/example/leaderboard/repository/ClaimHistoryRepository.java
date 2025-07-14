package com.example.leaderboard.repository;

import com.example.leaderboard.model.ClaimHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimHistoryRepository extends JpaRepository<ClaimHistory, Long> {
}
