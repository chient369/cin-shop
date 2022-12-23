package com.cinshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.cinshop"})
@SpringBootApplication
public class ShopWebUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShopWebUserApplication.class, args);	
	}
}
