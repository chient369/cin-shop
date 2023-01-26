package com.cinshop.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.customer.LoginUserDetails;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/p/fav")
public class FavoriteProductController {
	private final Integer ITEM_PER_PAGE = 12;
	
	@Autowired
	private FavoriteProductService fService;
	
	@GetMapping("")
	public String viewFavoriteProductPage(Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		
		return viewPageFavoriteProduct(1, model, userDetails);
	}

	@GetMapping("/page/{pnum}")
	public String viewPageFavoriteProduct(HttpServletRequest request, @PathVariable Integer pnum, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		Pageable pageable = PageRequest.of(pnum - 1, ITEM_PER_PAGE);
		
		
		Page<FavoriteProduct> page = fService.findByCustomer(userDetails.getCustomer().get().getId(), pageable);
		
		model.addAttribute("totalPages", page.getTotalPages() > 0 ? page.getTotalPages() : 1);
		model.addAttribute("currentPage", page.getNumber());

		//レビューの平均集計
		avgVoteCalc(page);
		
		//お気に入り登録しているか判定
		if (userDetails != null) {
			for (int i = 0; i < page.getContent().size(); i++) {
				page.getContent().get(i).getProductDetail().setFavoriteChecked(true);
			}
			model.addAttribute("products", page.getContent());
			model.addAttribute("custId", userDetails.getCustomer().get().getId());
			
		} else {
			model.addAttribute("products", session.getAttribute(cart));
		}
		return "product/product-favorite";
	}
	
	//ここから平均評価の集計
	private float avgVoteCalc(Page<FavoriteProduct> page) { 
		float avgVote = 0.0F;
		float totalVote = 0.0F;
		for (int i = 0; i < page.getContent().size(); i++) {
			for (int j = 0; j < page.getContent().get(i).getProductDetail().getReviews().size(); j++) {
				totalVote += page.getContent().get(i).getProductDetail().getReviews().get(j).getVote().floatValue();
			}
			avgVote = totalVote / page.getContent().get(i).getProductDetail().getReviews().size();
			avgVote = ((float)Math.round(avgVote * 10))/10;
			page.getContent().get(i).getProductDetail().setAvgVote(avgVote);
			totalVote = 0.0F;
			avgVote = 0.0F;
		}
		return avgVote;
	}
}
