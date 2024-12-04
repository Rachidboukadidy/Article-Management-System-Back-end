package com.MarketApp.article_management_system.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

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

    }
