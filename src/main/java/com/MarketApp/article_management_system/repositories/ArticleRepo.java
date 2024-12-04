package com.MarketApp.article_management_system.repositories;

import com.MarketApp.article_management_system.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article,Long> {

    // get all articles by city    ?????????/

    @Query(value = "select  art from  Article art where art.city = :city")
    List<Article> findByCity(@Param("city") String City);

    //get all the articles of user
    @Query(value = "select al from Article al where al.user.id = :userId")
    List<Article> findAllArticlesByUserId(@Param("userId") long userId);


}
