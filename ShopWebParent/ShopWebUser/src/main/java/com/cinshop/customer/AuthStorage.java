package com.cinshop.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AuthStorage {
	private static Map<String,String> auth;
	private static AuthStorage INSTANCE;

	private AuthStorage() {
		auth = new HashMap<String,String>();
	}
	
	public static AuthStorage getInstance(String custId,String code) {
		if(INSTANCE == null) {
			INSTANCE = new AuthStorage();
		}
		auth.put(custId, code);
		return INSTANCE;
	}
	
	public void remove(String custId) {
		auth.remove(custId);
	}
	
	public String getCode(String custId) {
		return auth.get(custId);
	}
}
