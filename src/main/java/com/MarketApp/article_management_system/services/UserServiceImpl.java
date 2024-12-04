package com.MarketApp.article_management_system.services;

import com.MarketApp.article_management_system.dto.Request.UserRequest;
import com.MarketApp.article_management_system.dto.Response.UserResponse;
import com.MarketApp.article_management_system.entities.User;
import com.MarketApp.article_management_system.repositories.UserRepo;
import com.MarketApp.article_management_system.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    public UserResponse addUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhone(userRequest.getPhone());
        user.setImageUrl(userRequest.getImageUrl());
        user.setCountry(userRequest.getCountry());
        user.setCity(userRequest.getCity());
        user.setState(userRequest.getState());
        user.setZip(userRequest.getZip());


        userRepo.save(user);



        return new UserResponse(user);

    }
    public UserResponse getUserById(long id) {
        Optional<User> user = userRepo.findById(id);
        UserResponse userResponse = new UserResponse();
        if (user.isPresent()) {
          User user1 = user.get();
          userResponse.setId(id);
          userResponse.setFirstName(user1.getFirstName());
          userResponse.setLastName(user1.getLastName());
          userResponse.setEmail(user1.getEmail());
          userResponse.setPassword(user1.getPassword());
          userResponse.setPhone(user1.getPhone());
          userResponse.setImageUrl(user1.getImageUrl());
          userResponse.setCountry(user1.getCountry());
          userResponse.setCity(user1.getCity());
          userResponse.setState(user1.getState());
          userResponse.setZip(user1.getZip());
            return userResponse;

        }else {
            return null;
        }


    }
    @Override
    public void updateUser( long id ,UserRequest userRequest) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            User newUser = user.get();
            newUser.setFirstName(userRequest.getFirstName());
            newUser.setLastName(userRequest.getLastName());
            newUser.setEmail(userRequest.getEmail());
            newUser.setPassword(userRequest.getPassword());
            newUser.setPhone(userRequest.getPhone());
            newUser.setImageUrl(userRequest.getImageUrl());
            newUser.setCountry(userRequest.getCountry());
            newUser.setCity(userRequest.getCity());
            newUser.setState(userRequest.getState());
            newUser.setZip(userRequest.getZip());
            userRepo.save(newUser);

        }
    }
    @Override
    public void deleteUser(long id ){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
        }
    }



}
