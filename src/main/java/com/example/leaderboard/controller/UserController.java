package com.example.leaderboard.controller;

import com.example.leaderboard.model.ClaimHistory;
import com.example.leaderboard.model.User;
import com.example.leaderboard.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://your-frontend.vercel.app") // ✅ Best for production
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User addUser(@RequestParam String name) {
        return userService.addUser(name);
    }

    @PostMapping("/users/{id}/claim")
    public User claimPoints(@PathVariable Long id) {
        return userService.claimPoints(id);
    }

    @GetMapping("/leaderboard")
    public List<User> getLeaderboard() {
        return userService.getLeaderboard();
    }

    @GetMapping("/history")
    public List<ClaimHistory> getHistory() {
        return userService.getHistory();
    }
}