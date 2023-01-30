package com.cinshop.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Review;
import com.cinshop.customer.LoginUserDetails;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewService service;
	
	@RequestMapping(path = "/p", method = RequestMethod.POST)
	public String saveReview(@ModelAttribute Review review, String detailId, @AuthenticationPrincipal LoginUserDetails userDetails, RedirectAttributes redirectAttributes){
		if (userDetails != null) {
			
			ProductDetail productDetail = new ProductDetail();
			productDetail.setId(Integer.valueOf(detailId));
			
			review.setCustomer(userDetails.getCustomer().get());
			review.setProductDetail(productDetail);
			service.addReview(review);
			
			redirectAttributes.addAttribute("pId", detailId);
			
		}
		return "redirect:/p/{pId}";
	}
}
