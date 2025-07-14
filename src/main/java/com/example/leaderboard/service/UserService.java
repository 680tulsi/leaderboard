package com.example.leaderboard.service;

import com.example.leaderboard.model.ClaimHistory;
import com.example.leaderboard.model.User;
import com.example.leaderboard.repository.ClaimHistoryRepository;
import com.example.leaderboard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final ClaimHistoryRepository historyRepo;

    public UserService(UserRepository userRepo, ClaimHistoryRepository historyRepo) {
        this.userRepo = userRepo;
        this.historyRepo = historyRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addUser(String name) {
        if (userRepo.findByName(name).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        return userRepo.save(
                User.builder()
                        .name(name)
                        .build()
        );
    }

    public User claimPoints(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        int randomPoints = new Random().nextInt(10) + 1;
        user.setTotalPoints(user.getTotalPoints() + randomPoints);
        userRepo.save(user);

        ClaimHistory history = ClaimHistory.builder()
                .user(user)
                .points(randomPoints)
                .claimedAt(LocalDateTime.now())
                .build();
        historyRepo.save(history);

        return user;
    }

    public List<User> getLeaderboard() {
        return userRepo.findAll()
                .stream()
                .sorted((u1, u2) -> Integer.compare(u2.getTotalPoints(), u1.getTotalPoints()))
                .toList();
    }

    public List<ClaimHistory> getHistory() {
        return historyRepo.findAll();
    }
}
