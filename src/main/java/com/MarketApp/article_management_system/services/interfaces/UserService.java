package com.MarketApp.article_management_system.services.interfaces;

import com.MarketApp.article_management_system.dto.Request.UserRequest;
import com.MarketApp.article_management_system.dto.Response.UserResponse;

public interface UserService {
     UserResponse addUser(UserRequest userRequest);
     void updateUser( long id ,UserRequest userRequest);
     void deleteUser(long id );
     UserResponse getUserById(long id);
}
