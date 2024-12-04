package com.MarketApp.article_management_system.repositories;

import com.MarketApp.article_management_system.entities.Category;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Integer > {
    
    @Query(value = "select ct from Category ct JOIN ct.users u where u.id = :userId")
    List<Category> getAllCategoriesByUserId(@PathVariable("userId") long userId);

//    Optional<Category> findByName(String name);
}
