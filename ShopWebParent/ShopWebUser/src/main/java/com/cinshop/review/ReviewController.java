package com.cinshop.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cinshop.common.entity.Product;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService service;
	
	@RequestMapping(path = "/p", method = RequestMethod.POST)
	public String saveReview(Model model, Product pId, String contect){	
		
		return "register";
	}
}
