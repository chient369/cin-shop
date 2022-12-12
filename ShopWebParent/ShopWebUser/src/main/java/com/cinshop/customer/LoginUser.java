package com.cinshop.customer;

import java.util.List;

public record LoginUser(String email, String userName, String password, List<String> roleList) {
	
}