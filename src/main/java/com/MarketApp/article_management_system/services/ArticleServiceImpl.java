package com.MarketApp.article_management_system.services;

import com.MarketApp.article_management_system.dto.Request.ArticleRequest;
import com.MarketApp.article_management_system.dto.Response.ArticleResponse;
import com.MarketApp.article_management_system.entities.Article;
import com.MarketApp.article_management_system.entities.Localisation;
import com.MarketApp.article_management_system.entities.User;
import com.MarketApp.article_management_system.repositories.ArticleRepo;
import com.MarketApp.article_management_system.repositories.CategoryRepo;
import com.MarketApp.article_management_system.repositories.LocalisationRepo;
import com.MarketApp.article_management_system.repositories.UserRepo;
import com.MarketApp.article_management_system.services.interfaces.ArticleService;
import com.MarketApp.article_management_system.services.interfaces.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LocalisationRepo localisationRepo;

    @Autowired
    private LocalisationService localisationService;

    // add an  article
    @Override
    public ArticleResponse addArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        article.setPrice(articleRequest.getPrice());
        article.setImageUrl(articleRequest.getImageUrl());
        article.setCity(articleRequest.getCity());
        article.setProvince(articleRequest.getProvince());



        // Associate  an existing user with an article
        User user = userRepo.findById(articleRequest.getUserid())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + articleRequest.getUserid()));
        article.setUser(user);
        articleRepo.save(article);

        // Add article's localisation
        Localisation localisation =new Localisation();
        if (articleRequest.getCity() !=null && articleRequest.getProvince() != null){
            double[] coordinates = localisationService.getCoordinates(articleRequest.getCity(), articleRequest.getProvince());

            localisation.setLatitude(coordinates[0]);
            localisation.setLongitude(coordinates[1]);
        }
        localisation.getArticles().add(article);
        localisationRepo.save(localisation);




        return new ArticleResponse(article) ;

    }

    // get an article
    public ArticleResponse getArticleById(long id) {
        Optional<Article> article = articleRepo.findById(id);
        ArticleResponse articleResponse = new ArticleResponse();
        if (article.isPresent()) {
            Article article1 = article.get();
            articleResponse.setId(article1.getId());
            articleResponse.setTitle(article1.getTitle());
            articleResponse.setDescription(article1.getDescription());
            articleResponse.setPrice(article1.getPrice());
            articleResponse.setImageUrl(article1.getImageUrl());
        }
        return articleResponse;
    }

    // get All Articles
    public List<ArticleResponse> getAllArticles() {
        List<Article> articles = articleRepo.findAll();
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article.getId());
            articleResponse.setTitle(article.getTitle());
            articleResponse.setDescription(article.getDescription());
            articleResponse.setPrice(article.getPrice());
            articleResponse.setImageUrl(article.getImageUrl());
            articleResponses.add(articleResponse);
        }
        return articleResponses;
    }

    // update an article
    @Override
    public void updateArticle(long id, ArticleRequest articleRequest) {
        Optional<Article> article = articleRepo.findById(id);
        if (article.isPresent()){
            Article article1 = article.get();
            article1.setId(id);
            article1.setTitle(articleRequest.getTitle());
            article1.setDescription(articleRequest.getDescription());
            article1.setPrice(articleRequest.getPrice());
            article1.setImageUrl(articleRequest.getImageUrl());
            article1.setCity(articleRequest.getCity());
            article1.setProvince(articleRequest.getProvince());
            articleRepo.save(article1);
        }
    }

    // delete an article
    @Override
    public void deleteArticle(long id) {
        Optional<Article> article = articleRepo.findById(id);
        article.ifPresent(value -> articleRepo.delete(value));
    }

    // get articles by city
    public List<ArticleResponse>  searchByUserCity(String city){
        List<Article> articles = articleRepo.findByCity(city);
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article1 : articles) {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article1.getId());
            articleResponse.setTitle(article1.getTitle());
            articleResponse.setDescription(article1.getDescription());
            articleResponse.setPrice(article1.getPrice());
            articleResponse.setImageUrl(article1.getImageUrl());
            articleResponses.add(articleResponse);
        }
        return articleResponses;
    }

    // get All Articles of user specific
    public List<ArticleResponse> getAllArticlesByUserId(long userId) {
        List<Article> articles = articleRepo.findAllArticlesByUserId(userId);
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article.getId());
            articleResponse.setTitle(article.getTitle());
            articleResponse.setDescription(article.getDescription());
            articleResponse.setPrice(article.getPrice());
            articleResponse.setImageUrl(article.getImageUrl());
            articleResponses.add(articleResponse);
        }
        return articleResponses;
    }

    // get All Articles of localisation's user
    public List<ArticleResponse> getAllArticlesByLocalisation(Double latitude ,Double longitude, Double distance) {
        List<Article> articles = localisationRepo.findArticleByLocalisationProximity(latitude,longitude,distance);
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(article.getId());
            articleResponse.setTitle(article.getTitle());
            articleResponse.setDescription(article.getDescription());
            articleResponse.setPrice(article.getPrice());
            articleResponse.setImageUrl(article.getImageUrl());
            articleResponses.add(articleResponse);
        }
        return articleResponses;
    }



}
