package com.cinshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cinshop"})
public class ShopWebUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopWebUserApplication.class, args);
	}

}
