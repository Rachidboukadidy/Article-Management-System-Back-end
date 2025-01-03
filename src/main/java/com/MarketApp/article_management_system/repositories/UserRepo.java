package com.MarketApp.article_management_system.repositories;

import com.MarketApp.article_management_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface UserRepo extends JpaRepository<User,Long> {
//    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
