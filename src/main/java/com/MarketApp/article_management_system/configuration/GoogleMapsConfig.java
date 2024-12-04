package com.MarketApp.article_management_system.configuration;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMapsConfig {

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey("AIzaSyDK4rhbAXt1vZMGDaoxHgt3E84KEQjpv9Y")
                .build();
    }
}
