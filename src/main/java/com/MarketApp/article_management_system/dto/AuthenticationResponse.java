package com.MarketApp.article_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String token ;
}
