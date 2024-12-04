package com.MarketApp.article_management_system.services.interfaces;

import com.MarketApp.article_management_system.entities.Localisation;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.stereotype.Repository;


public interface LocalisationService {

    double[] getCoordinates(String city, String province);
//  void addArticleLocalisation(Double lat, Double lon,long id );

    GeocodingResult[] geocodeAddress(String address) throws Exception;

    LatLng getLatLngForAddress(String address) throws Exception;
}
