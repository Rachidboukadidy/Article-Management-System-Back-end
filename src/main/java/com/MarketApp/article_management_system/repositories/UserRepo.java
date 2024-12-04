package com.MarketApp.article_management_system.repositories;

import com.MarketApp.article_management_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
