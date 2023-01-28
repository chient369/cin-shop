package com.cinshop.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.customer.LoginUserDetails;

@Controller
@RequestMapping("/p/fav")
public class FavoriteProductController {
	private final Integer ITEM_PER_PAGE = 12;
	
	@Autowired
	private FavoriteProductService fService;
	
	@Autowired
	private ProductDetailService dService;
	
	@GetMapping("")
	public String viewFavoriteProductPage(Model model, @AuthenticationPrincipal LoginUserDetails userDetails, @CookieValue(name= "key", required = false, defaultValue = "no data") String v) {
		return viewPageFavoriteProduct(1, model, userDetails, v);
	}
	
	@GetMapping("/page/{pnum}")
	public String viewPageFavoriteProduct(@PathVariable Integer pnum, Model model, @AuthenticationPrincipal LoginUserDetails userDetails, String v) {
		Pageable pageable = PageRequest.of(pnum - 1, ITEM_PER_PAGE);
		Page<FavoriteProduct> page = Page.empty(pageable);
		
		//会員ユーザー
		if (userDetails != null) {
			page = fService.findByCustomer(userDetails.getCustomer().get().getId(), pageable);
			
		//ゲストユーザー	
		} else {
			if (!v.equals("no data")) {
				String[] values = v.split(",");
				List<FavoriteProduct> list = new ArrayList<FavoriteProduct>();
				for (int i = 0; i < values.length; i++) {
					list.add(i, new FavoriteProduct());
					list.get(i).setProductDetail(dService.findById(Integer.parseInt(values[i])));
				}
				
				//listからページに詰め替え
				long start = pageable.getOffset();
				long end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
				page = new PageImpl<FavoriteProduct>(list.subList((int)start, (int)end), pageable, list.size());
				
			//件数0は処理を行わず画面表示	
			} else {
				model.addAttribute("totalPages", page.getTotalPages() > 0 ? page.getTotalPages() : 1);
				model.addAttribute("currentPage", page.getNumber());
				model.addAttribute("products", page.getContent());
				return "product/product-favorite";
			}
		}
		//レビューの平均集計
		avgVoteCalc(page);
		
		for (int i = 0; i < page.getContent().size(); i++) {
			page.getContent().get(i).getProductDetail().setFavoriteChecked(true);
		}	
		
		model.addAttribute("totalPages", page.getTotalPages() > 0 ? page.getTotalPages() : 1);
		model.addAttribute("currentPage", page.getNumber());
		model.addAttribute("products", page.getContent());
		
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
