package com.MarketApp.article_management_system.repositories;

import com.MarketApp.article_management_system.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

}
