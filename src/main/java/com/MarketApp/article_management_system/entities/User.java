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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String password;
    private String phone;
    private String imageUrl;
    private String Country;
    private String City;
    private String State;
    private String Zip;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Article> articles;

    @ManyToMany(mappedBy = "users")
    private List<Category> categories;

//    @OneToOne
//    @JoinColumn(name = "Localisation_id",referencedColumnName ="id")
//    private Localisation localisation;
}
