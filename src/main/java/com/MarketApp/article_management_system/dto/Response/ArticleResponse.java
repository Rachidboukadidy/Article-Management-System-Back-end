package com.MarketApp.article_management_system.dto.Response;

import com.MarketApp.article_management_system.entities.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleResponse {
    private  long id ;
    private String title ;
    private String description ;
    private Double price ;
    private String imageUrl ;



    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.price = article.getPrice();
        this.imageUrl = article.getImageUrl();




    }
}
