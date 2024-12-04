package com.MarketApp.article_management_system.services.interfaces;

import com.MarketApp.article_management_system.dto.Response.CategoryResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryService {
    List<CategoryResponse> getAllCategoriesByUserID(long userID);
    List <CategoryResponse> getAllCategories();
}
