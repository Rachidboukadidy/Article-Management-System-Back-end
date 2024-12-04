package com.MarketApp.article_management_system.services;

import com.MarketApp.article_management_system.dto.Response.CategoryResponse;
import com.MarketApp.article_management_system.entities.Category;
import com.MarketApp.article_management_system.repositories.CategoryRepo;
import com.MarketApp.article_management_system.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepository;

//    Get all categories of an user
    public List <CategoryResponse> getAllCategoriesByUserID(long userID) {
        List<Category> categories =categoryRepository.getAllCategoriesByUserId(userID);
        List <CategoryResponse> categoriesResponse = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setName(category.getName());
            categoryResponse.setDescription(category.getDescription());
            categoryResponse.setImageUrl(category.getImageUrl());
            categoriesResponse.add(categoryResponse);

        }
        return categoriesResponse;

    }
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List <CategoryResponse>categoriesResponse = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setName(category.getName());
            categoryResponse.setDescription(category.getDescription());
            categoryResponse.setImageUrl(category.getImageUrl());
            categoriesResponse.add(categoryResponse);

        }
        return categoriesResponse;
    }
}
