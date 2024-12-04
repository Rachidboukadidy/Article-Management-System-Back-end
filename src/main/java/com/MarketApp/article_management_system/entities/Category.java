package com.MarketApp.article_management_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String imageUrl;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Article> articles;

   @ManyToMany
   @JoinTable(
           name = "User_CategoriesArticles",
           joinColumns = @JoinColumn(name = "userID"),
           inverseJoinColumns = @JoinColumn(name = "categoryId")
   )
    private List<User> users;
}
