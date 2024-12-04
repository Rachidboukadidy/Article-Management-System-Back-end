package com.MarketApp.article_management_system.services.interfaces;

import com.MarketApp.article_management_system.dto.Request.ArticleRequest;
import com.MarketApp.article_management_system.dto.Response.ArticleResponse;

import java.util.List;

public interface ArticleService {
    // add an  article
    ArticleResponse addArticle(ArticleRequest articleRequest);

    // get an article
    ArticleResponse getArticleById(long id);

    // get All Articles
    List<ArticleResponse> getAllArticles();

    // update an article
    void updateArticle(long id, ArticleRequest articleRequest);

    // delete an article
    void deleteArticle(long id);

    List<ArticleResponse>  searchByUserCity(String city);

    List<ArticleResponse> getAllArticlesByUserId(long userId);

    List<ArticleResponse> getAllArticlesByLocalisation(Double latitude ,Double longitude, Double distance);
}
