package com.MarketApp.article_management_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id ;

    private String title ;
    private String description ;
    private Double price ;
    private String imageUrl ;
    private String city ;
    private String province ;

    @ManyToOne
    private Localisation localisation ;


    @ManyToOne
    @JoinColumn(name = "user-id", nullable = false)
     private User user;

//    @OneToOne
//    @JoinColumn(name = "Localisation_id",referencedColumnName ="id")
//    private Localisation localisation;
}
