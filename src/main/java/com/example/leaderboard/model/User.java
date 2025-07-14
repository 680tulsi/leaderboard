package com.example.leaderboard.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // ✅ avoids conflict with reserved keyword
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    private int totalPoints = 0;
}
