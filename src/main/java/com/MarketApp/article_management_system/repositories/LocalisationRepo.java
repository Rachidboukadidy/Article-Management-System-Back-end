package com.MarketApp.article_management_system.repositories;

import com.MarketApp.article_management_system.entities.Article;
import com.MarketApp.article_management_system.entities.Localisation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.lang.Math.acos;
import static java.lang.Math.cos;

@Repository
public interface LocalisationRepo extends JpaRepository<Localisation,Long> {

    @Query(value = "Select ar from Article ar where "+
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(ar.localisation.latitude)) * " +
            "cos(radians(ar.localisation.longitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(ar.localisation.latitude)))) < :distance")
    List<Article> findArticleByLocalisationProximity(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("distance") Double distance
    );
@Modifying
@Transactional
@Query("UPDATE Localisation l SET l.latitude = :latitude, l.longitude = :longitude WHERE l.user.id = :userId")
void addLocalisationOfUser(Double latitude, Double longitude, Long userId);
}

