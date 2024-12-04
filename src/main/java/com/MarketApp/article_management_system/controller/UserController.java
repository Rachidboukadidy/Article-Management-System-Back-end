package com.MarketApp.article_management_system.controller;

import com.MarketApp.article_management_system.dto.Request.UserRequest;
import com.MarketApp.article_management_system.dto.Response.CategoryResponse;
import com.MarketApp.article_management_system.dto.Response.UserResponse;
import com.MarketApp.article_management_system.services.interfaces.CategoryService;
import com.MarketApp.article_management_system.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }
    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);

    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }

//    Get all categories by userId
    @GetMapping("/categoriesByUser/{userId}")
    public ResponseEntity<List<CategoryResponse>> getCategoriesByUserId(@PathVariable long userId) {
       List<CategoryResponse> categoryResponse = categoryService.getAllCategoriesByUserID(userId);
       return ResponseEntity.ok(categoryResponse);
    }
//     Get all categories without userFilter
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponse = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryResponse);
    }


}
