package com.cinshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.cinshop" })
@EntityScan(basePackages = { "com.cinshop.*" })
public class ShopWebAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopWebAdminApplication.class, args);
	}

}
