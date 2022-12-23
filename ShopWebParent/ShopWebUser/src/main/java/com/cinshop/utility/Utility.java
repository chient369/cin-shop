package com.cinshop.utility;

import jakarta.servlet.http.HttpServletRequest;

public class Utility {

	public static String getEmailAuthenticatedCustomer(HttpServletRequest request) {
		Object principal = request.getUserPrincipal();
		if (principal == null)
			return null;

		String email = request.getUserPrincipal().getName();
		return email;
	}
}
