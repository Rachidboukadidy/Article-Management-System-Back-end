package com.MarketApp.article_management_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
