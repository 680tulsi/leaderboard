package com.example.leaderboard.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int points;

    private LocalDateTime claimedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
