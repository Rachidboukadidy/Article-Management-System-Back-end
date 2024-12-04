package com.MarketApp.article_management_system.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleRequest {
    private String title ;
    private String description ;
    private Double price ;
    private String imageUrl ;
    private long userid;
    private String city;
    private String province;

}
