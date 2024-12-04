package com.MarketApp.article_management_system.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.MarketApp.article_management_system.entities")
@EnableJpaRepositories("com.MarketApp.article_management_system.repositories")
@ComponentScan(basePackages = "com.MarketApp")

//@ComponentScan({"com.MarketApp.article_management_system.services", "com.MarketApp.article_management_system.controller",})

public class ArticleManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleManagementSystemApplication.class, args);
	}

}
