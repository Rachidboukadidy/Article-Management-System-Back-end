package com.MarketApp.article_management_system.Config;

import com.google.maps.GeoApiContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMapsConfig {

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey("AIzaSyB2jD1CvmNPyYIQ1Hg-B4IQvaPkX3Kk1-0")
                .build();
    }

}
