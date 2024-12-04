package com.MarketApp.article_management_system.controller;

import com.MarketApp.article_management_system.dto.Request.ArticleRequest;
import com.MarketApp.article_management_system.dto.Response.ArticleResponse;
import com.MarketApp.article_management_system.repositories.CategoryRepo;
import com.MarketApp.article_management_system.repositories.LocalisationRepo;
import com.MarketApp.article_management_system.repositories.UserRepo;
import com.MarketApp.article_management_system.services.interfaces.ArticleService;
import com.MarketApp.article_management_system.services.interfaces.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    LocalisationService localisationService;


    @PostMapping()
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse =  articleService.addArticle(articleRequest);
        return articleResponse != null ? ResponseEntity.ok(articleResponse) : ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable long id) {
        ArticleResponse articleResponse = articleService.getArticleById(id);
        return ResponseEntity.ok().body(articleResponse);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<ArticleResponse> articleResponses = articleService.getAllArticles();
        return ResponseEntity.ok().body(articleResponses);
    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable long id, @RequestBody ArticleRequest articleRequest) {
        articleService.updateArticle(id, articleRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable long id){
        articleService.deleteArticle(id);
    }

    //search for an article by city
    @GetMapping("/citySearch")
    public ResponseEntity<List<ArticleResponse>> searchByCity(@RequestParam String city) {
        List<ArticleResponse> articlesByCity = articleService.searchByUserCity(city);
        return ResponseEntity.ok().body(articlesByCity);
    }

    //get all articles of user
    @GetMapping("userId/{id}")
    public ResponseEntity<List<ArticleResponse>> getAllArticlesByUserId(@PathVariable("id") long  userId){
        List<ArticleResponse> articleResponseList = articleService.getAllArticlesByUserId(userId);
        return ResponseEntity.ok().body(articleResponseList);

    }

    //get all articles by localisation
    @GetMapping("/searchByLocalisation")
    public ResponseEntity<List<ArticleResponse>> getAllArticlesByLocalisation(
            @RequestParam(name = "lat",required = false) Double latitude,
            @RequestParam(name = "long",required = false) Double longitude,
            @RequestParam(name="distance",required = false) Double distance
            ){

        List<ArticleResponse> articleResponseList = articleService.getAllArticlesByLocalisation(latitude, longitude, distance);
        if (articleResponseList.isEmpty()) {
             throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Articles not found");

        }else
            return ResponseEntity.ok().body(articleResponseList);

    }
//    @PostMapping("/addLocalisationArticle")
//    public void AddArticleLocalisation(
//            @RequestParam(name ="lat",required = false ) Double latitude,
//            @RequestParam(name="lon",required=false) Double longitude,
//            @RequestParam(name="idUser",required = false) long id
//    ){
//        if(latitude == null || longitude == null || id == 0){
//            throw new RuntimeException("your position information is incorrect");
//        }else {
//            localisationService.addArticleLocalisation(latitude,longitude,id); ;
//        }
//
//    }


}
