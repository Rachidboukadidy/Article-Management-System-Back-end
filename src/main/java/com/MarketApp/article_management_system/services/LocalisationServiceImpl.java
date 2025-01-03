package com.MarketApp.article_management_system.services;

import com.MarketApp.article_management_system.entities.Localisation;
import com.MarketApp.article_management_system.repositories.LocalisationRepo;
import com.MarketApp.article_management_system.repositories.UserRepo;
import com.MarketApp.article_management_system.services.interfaces.LocalisationService;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;


@Service
public class LocalisationServiceImpl implements LocalisationService {
    @Autowired
    private LocalisationRepo localisationRepo;
    @Autowired
    private UserRepo userRepo;

    private final GeoApiContext context;

    @Autowired
    public LocalisationServiceImpl(GeoApiContext context) {
        this.context = context;
    }


    // Add user localisation

    public void addArticleLocalisation(Double lat, Double lon,long id ) {
        Localisation localisation = new Localisation();
        localisation.setId(id);
        localisation.setLatitude(lat);
        localisation.setLongitude(lon);
        localisationRepo.addLocalisationOfUser(lat,lon,id);
    }



    // methode for get latitude and longitude of an article

//    @Value("${google.api.key}")
//    private String API_KEY;
//    private final String GEOCODING_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";
//
//    public double[] getCoordinates(String city, String province) {
//        RestTemplate restTemplate = new RestTemplate();
//        String address =  province + "," + city ;
//        String url = GEOCODING_API_URL + "?address=" + address.replace(" ", "+") + "&key=" + API_KEY;
//
//        try {
//            // Fetch response from API
//            String response = restTemplate.getForObject(url, String.class);
//
//            // Parse JSON response
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode root = objectMapper.readTree(response);
//            JsonNode results = root.path("results");
//
//            if (results.isArray() && !results.isEmpty()) {
//                JsonNode location = results.get(0).path("geometry").path("location");
//
//                double latitude = location.path("lat").asDouble();
//                double longitude = location.path("lng").asDouble();
//
//                return new double[]{latitude, longitude};
//            } else {
//                throw new RuntimeException("Aucune coordonnée trouvée pour l'adresse : " + address);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Échec de la récupération des coordonnées pour l'adresse : " + address, e);
//        }
//    }


    @Override
    public double[] getCoordinates(String city, String province) {
        return new double[0];
    }

    public GeocodingResult[] geocodeAddress(String address) throws Exception {
        return GeocodingApi.geocode(context, address).await();
    }
    public LatLng getLatLngForAddress(String address) throws Exception {
        GeocodingResult[] results = geocodeAddress(address);
        if (results != null && results.length > 0) {
            return results[0].geometry.location; // Latitude and Longitude are encapsulated in LatLng
        } else {
            throw new Exception("Address not found: " + address);
        }
    }
}
