package com.MarketApp.article_management_system.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryResponse {
    private String name;
    private String description;
    private String imageUrl;
}
