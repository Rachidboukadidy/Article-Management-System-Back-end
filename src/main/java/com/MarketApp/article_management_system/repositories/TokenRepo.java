package com.MarketApp.article_management_system.repositories;

import com.MarketApp.article_management_system.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String token);
}
