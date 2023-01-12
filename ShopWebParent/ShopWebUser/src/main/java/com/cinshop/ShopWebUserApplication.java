package com.cinshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@ComponentScan(basePackages = {"com.cinshop"})
@SpringBootApplication
public class ShopWebUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShopWebUserApplication.class, args);	
	}
	@Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
