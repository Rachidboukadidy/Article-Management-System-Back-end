package com.MarketApp.article_management_system.services;

import com.MarketApp.article_management_system.dto.AuthenticationRequest;
import com.MarketApp.article_management_system.dto.AuthenticationResponse;
import com.MarketApp.article_management_system.entities.RegistrationRequest;
import com.MarketApp.article_management_system.entities.Token;
import com.MarketApp.article_management_system.entities.User;
import com.MarketApp.article_management_system.repositories.RoleRepo;
import com.MarketApp.article_management_system.repositories.TokenRepo;
import com.MarketApp.article_management_system.repositories.UserRepo;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;
    private final EmailService emailService;
    private  final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;


    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

//     Registration methode
    public void register(RegistrationRequest request) throws MessagingException {

        var userRole =roleRepo.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("User Role Not Found"));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepo.save(user);
        sendValidationEmail(user);

    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken =generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.FullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "account activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        //generate token
        String generatedToken =generateActivationCode(6) ;
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(30))
                .user(user)
                .build();
        tokenRepo.save(token);
        return  generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters ="0123456789";
        StringBuilder activationCode =new StringBuilder();
        SecureRandom secureRandom =new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomChar = secureRandom.nextInt(characters.length());
            activationCode.append(characters.charAt(randomChar));
        }
        return activationCode.toString();
    }

    // Authentication methode

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ) );
        var claims =new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName    ", user.FullName());
        var jwt = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }

    // activation Account

    public void activationAccount(String token) throws MessagingException {
       Token savedToken = tokenRepo.findByToken(token)
                .orElseThrow(()->new RuntimeException("Invalid token"));
       if(LocalDateTime.now().isAfter(savedToken.getCreatedAt().plusMinutes(30))){
           sendValidationEmail(savedToken.getUser());
           throw new MessagingException("Activation token has expired,a new token has been send");
       }
       var user =userRepo.findById(savedToken.getUser().getId())
               .orElseThrow(()->new UsernameNotFoundException("User Not Found")) ;
       user.setEnabled(true);
       userRepo.save(user);
       savedToken.setValidatedAt(LocalDateTime.now());
       tokenRepo.save(savedToken);

    }




}
