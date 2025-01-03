package com.MarketApp.article_management_system.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails , Principal {
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

    private boolean accountLocked;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private List <Role> roles;

    @CreatedDate
    @Column(nullable = false, updatable = false,columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime createdDate ;

    @LastModifiedDate
    @Column(insertable = false )
    private LocalDateTime lastModifiedDate ;



    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Article> articles;

    @ManyToMany(mappedBy = "users")
    private List<Category> categories;




    @Override
    public String getName() {
        return email;
    }
//    for getting all the authorities of user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return enabled;

    }
    public String FullName() {
        return firstName + " " + lastName;
    }

//    @OneToOne
//    @JoinColumn(name = "Localisation_id",referencedColumnName ="id")
//    private Localisation localisation;
}
