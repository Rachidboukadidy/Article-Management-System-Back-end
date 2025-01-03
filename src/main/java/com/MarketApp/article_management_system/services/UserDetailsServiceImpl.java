package com.MarketApp.article_management_system.services;

import com.MarketApp.article_management_system.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService    {

    private final UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepo.findByEmail(userEmail)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
}
