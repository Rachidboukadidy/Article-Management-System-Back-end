package com.MarketApp.article_management_system.core;

import com.MarketApp.article_management_system.entities.Role;
import com.MarketApp.article_management_system.repositories.RoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan("com.MarketApp.article_management_system.entities")
@EnableJpaRepositories("com.MarketApp.article_management_system.repositories")
@ComponentScan(basePackages = "com.MarketApp")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class ArticleManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleManagementSystemApplication.class, args);

	}
  	@Bean
	public CommandLineRunner runner(RoleRepo roleRepo ) {
		return args -> {
			if(roleRepo.findByName("USER").isEmpty()){
				roleRepo.save(Role.builder().name("USER").build());
			}
		};
	}
}
