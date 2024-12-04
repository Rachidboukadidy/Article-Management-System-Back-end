package com.MarketApp.article_management_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Localisation {
    @Id
    private long  id;
    private Double latitude;
    private Double longitude;

    // One-to-Many relationship with Article
    @OneToMany(mappedBy = "localisation", cascade = CascadeType.ALL)
    private List<Article> articles;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}
