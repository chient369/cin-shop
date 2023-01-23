	package com.cinshop.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Review;
import com.cinshop.customer.LoginUserDetails;
import com.cinshop.review.ReviewService;

@Controller
@RequestMapping("/p")
public class ProductController {
	private final Integer ITEM_PER_PAGE = 12;

	@Autowired
	private ProductDetailService dService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService rService;
	
	@Autowired
	private FavoriteProductService fService;
	
	
	@GetMapping("")
	public String viewProductPage(Model model) {
		
		return viewPage(1, model);
	}

	/* ホームページ */

	@GetMapping("/page/{pnum}")
	public String viewPage(@PathVariable Integer pnum, Model model) {
		Pageable pageable = PageRequest.of(pnum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.finAll(pageable);

		model = responeCommonData(model, page.getNumber(), page.getTotalPages());

		//レビューの平均集計
		avgVoteCalc(page);
		model.addAttribute("products", page.getContent());
		
		return "product/product";
	}

	@GetMapping("/{pId}")
	public String viewProduct(@PathVariable Integer pId, Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		ProductDetail detail = dService.findById(6);
		
		model.addAttribute("p", detail.getProducts());
		model.addAttribute("detail", detail);
		model.addAttribute("colors", findExistColors(detail.getProducts()));
		model.addAttribute("sizes", dService.findAllSizes());
		
		//レビュー用
		float avgVote = ((float)Math.round(rService.getAvgRanking(6) * 10))/10;
		model.addAttribute("review", new Review());
		model.addAttribute("reviewList", detail.getReviews());
		model.addAttribute("avgVote", avgVote);
		
		//お気に入り用
		if (userDetails != null) {
			FavoriteProduct favoriteProduct = fService.findByCustomerAndDetailId(userDetails.getCustomer().get().getId(), pId);
			model.addAttribute("custId", userDetails.getCustomer().get().getId());
			if (favoriteProduct != null) {
				model.addAttribute("favoriteProduct", favoriteProduct.getClass());
			} else {
				model.addAttribute("favoriteProduct", null);
			}
		} else {
			model.addAttribute("custId", null);
			model.addAttribute("favoriteProduct", null);
		}
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("dId", detail.getId());
		return "product/product-detail";
	}

	/* テキストであいまい検索する */
	@GetMapping("/search/text")
	public String searchByTextFirstPage(@RequestParam("src-txt") String text, Model model) {
		return searchByText(text, 1, model);
	}

	@GetMapping("/search/text/{pNum}")
	public String searchByText(@RequestParam("src-txt") String text, @PathVariable Integer pNum, Model model) {
		if (pNum == null || pNum == 0)
			pNum = 1;
		String srcText = text.trim();
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.findByText(srcText, pageable);

		model = responeCommonData(model, page.getNumber(), page.getTotalPages());
		model.addAttribute("actionTag", "search");
		model.addAttribute("searchTag", "text");
		
		//レビューの平均集計
		avgVoteCalc(page);
		model.addAttribute("products", page.getContent());
		return "product/product";
	}

	/* カテゴリーで検索する */

	@GetMapping("/search/cat/{catId}")
	public String searchByCatFirstPage(@PathVariable Integer catId, Model model) {
		return searchByCategory(catId, 1, model);

	}

	@GetMapping("/search/cat/{catId}/{pNum}")
	public String searchByCategory(@PathVariable Integer catId, @PathVariable Integer pNum, Model model) {
		if (pNum == null || pNum == 0)
			pNum = 1;
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.findByCategory(catId, pageable);

		model = responeCommonData(model, page.getNumber(), page.getTotalPages());
		model.addAttribute("actionTag", "search");
		model.addAttribute("searchTag", "cat");
		model.addAttribute("tagId", catId);
		
		//レビューの平均集計
		avgVoteCalc(page);
		model.addAttribute("products", page.getContent());
		return "product/product";
	}

	/* ブランドで検索する */

	@GetMapping("/search/brand/{brandId}")
	public String searchByBrandFirstPage(@PathVariable Integer brandId, Model model) {
		return searchByBrand(brandId, 1, model);

	}

	@GetMapping("/search/brand/{brandId}/{pNum}")
	public String searchByBrand(@PathVariable Integer brandId, @PathVariable Integer pNum, Model model) {
		if (pNum == null || pNum == 0)
			pNum = 1;
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.findByCategory(brandId, pageable);

		model = responeCommonData(model, page.getNumber(), page.getTotalPages());
		model.addAttribute("actionTag", "search");
		model.addAttribute("searchTag", "brand");
		model.addAttribute("tagId", brandId);
		
		//レビューの平均集計
		avgVoteCalc(page);
		model.addAttribute("products", page.getContent());
		return "product/product";
	}

	/* カラーで検索する */
	@GetMapping("/search/color/{colorId}")
	public String searchByColorFirstPage(@PathVariable Integer colorId, Model model) {
		return searchByColor(colorId, 1, model);

	}

	@GetMapping("/search/color/{colorId}/{pNum}")
	public String searchByColor(@PathVariable Integer colorId, @PathVariable Integer pNum, Model model) {
		if (pNum == null || pNum == 0)
			pNum = 1;
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.findByColor(colorId, pageable);

		model = responeCommonData(model, page.getNumber(), page.getTotalPages());
		model.addAttribute("actionTag", "search");
		model.addAttribute("searchTag", "color");
		model.addAttribute("tagId", colorId);
		
		//レビューの平均集計
		avgVoteCalc(page);
		model.addAttribute("products", page.getContent());
		return "product/product";
	}

	/* 値段で検索する */
	@GetMapping("/search/price")
	public String searchByPriceFirstPage(@RequestParam("from") Integer pFrom, @RequestParam("to") Integer pTo,
			Model model) {
		return searchByPrice(pFrom, pTo, 1, model);
	}

	@GetMapping("/search/price/{pNum}")
	public String searchByPrice(@RequestParam("from") Integer pFrom, @RequestParam("to") Integer pTo, Integer pNum,
			Model model) {
		if (pNum == null || pNum == 0)
			pNum = 1;
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE);
		Page<ProductDetail> page = dService.findByPrice(pFrom, pTo, pageable);

		model = responeCommonData(model, page.getNumber(), page.getTotalPages());
		model.addAttribute("actionTag", "search");
		model.addAttribute("searchTag", "price");
		
		//レビューの平均集計
		avgVoteCalc(page);
		model.addAttribute("products", page.getContent());

		return "product/product";
	}

	/* 値段で並び替えする */
	@GetMapping("/sort/{sortBy}/{sortDir}")
	public String sortByPriceFirstPage(@PathVariable("sortBy") String sortBy, @PathVariable("sortDir") String sortDir,
			Model model) {
		return sortByPrice(sortBy, sortDir, 1, model);
	}

	@GetMapping("/sort/{sortBy}/{sortDir}/{pNum}")
	public String sortByPrice(@PathVariable("sortBy") String sortBy, @PathVariable("sortDir") String sortDir,
			Integer pNum, Model model) {
		if (pNum == null || pNum == 0)
			pNum = 1;
		Sort sort = initializeSort(sortBy, sortDir);
		Pageable pageable = PageRequest.of(pNum - 1, ITEM_PER_PAGE, sort);

		Page<ProductDetail> page = dService.finAll(pageable);
		
		//レビューの平均集計
		avgVoteCalc(page);
		
		//レビューのソート
		if (sortBy.equals("avgVote")) {
			//ソート、平均評価と商品の関連付け
			List<ProductDetail> newList = page.stream()
			        .sorted(Comparator.comparing(ProductDetail::getAvgVote, Comparator.reverseOrder())
	                .thenComparing(ProductDetail::getAvgVote))
			        .collect(Collectors.toList());
			
			//詰替え
			long start = pageable.getOffset();
			long end = (start + pageable.getPageSize()) > newList.size() ? newList.size() : (start + pageable.getPageSize());
			Page<ProductDetail> newPage = new PageImpl<ProductDetail>(newList.subList((int)start, (int)end), pageable, newList.size());
			model.addAttribute("products", newPage.getContent());	
		} else {
			model.addAttribute("products", page.getContent());
		}
		
		model = responeCommonData(model, page.getNumber(), page.getTotalPages());
		model.addAttribute("actionTag", "sort");
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortDir", sortDir);
		return "product/product";
	}

	private Sort initializeSort(String sortBy, String sortDir) {
		if (sortDir.equals("asc")) {
			return Sort.by(sortBy).ascending();
		}
		if (sortDir.equals("desc")) {

			return Sort.by(sortBy).descending();
		}
		return null;
	}

	private List<Color> findExistColors(Set<Product> products) {
		List<Color> colors = new ArrayList<>();
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product product = it.next();
			if (!colors.contains(product.getColor())) {
				colors.add(product.getColor());
			}
		}
		return colors;
	}

	private Model responeCommonData(Model model, Integer currentPage, Integer totalPages) {
		List<Color> colors = dService.findAllColors();
		List<Brand> brands = dService.findAllBranchs();
		List<Category> categories = dService.findAllCategories();

		model.addAttribute("totalPages", totalPages > 0 ? totalPages : 1);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("colors", colors);
		model.addAttribute("brands", brands);
		model.addAttribute("cats", categories);
		return model;
	}
	
	//ここから平均評価の集計
	private float avgVoteCalc(Page<ProductDetail> page) { 
		float avgVote = 0.0F;
		float totalVote = 0.0F;
		for (int i = 0; i < page.getContent().size(); i++) {
			for (int j = 0; j < page.getContent().get(i).getReviews().size(); j++) {
				totalVote += page.getContent().get(i).getReviews().get(j).getVote().floatValue();
			}
			avgVote = totalVote / page.getContent().get(i).getReviews().size();
			avgVote = ((float)Math.round(avgVote * 10))/10;
			page.getContent().get(i).setAvgVote(avgVote);
			totalVote = 0.0F;
			avgVote = 0.0F;
		}
		return avgVote;
	}
	
}
