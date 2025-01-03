package com.MarketApp.article_management_system.controller;

import com.MarketApp.article_management_system.dto.AuthenticationRequest;
import com.MarketApp.article_management_system.dto.AuthenticationResponse;
import com.MarketApp.article_management_system.services.AuthenticationService;
import com.MarketApp.article_management_system.entities.RegistrationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication") // for openApi
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/activate-account")
    public void activateAccount(@RequestParam String token) throws MessagingException {
        authenticationService.activationAccount(token);
    }

}
