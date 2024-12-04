package com.MarketApp.article_management_system.dto.Response;

import com.MarketApp.article_management_system.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String imageUrl;
    private String Country;
    private String City;
    private String State;
    private String Zip;


    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.imageUrl = user.getImageUrl();
        this.Country = user.getCountry();
        this.City = user.getCity();
        this.State = user.getState();
        this.Zip = user.getZip();
    }
}
