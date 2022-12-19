package com.cinshop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.Size;

@Service
public class ProductDetailService {

	@Autowired
	private ProductDetailRepository detailRepository;

	@Autowired
	private ProductUtility utility;

	public ProductDetail findById(Integer id){
		return detailRepository.findById(id).get(0);
	}

	public Page<ProductDetail> finAll(Pageable pageable) {
		return detailRepository.findAll(pageable);

	}

	public Page<ProductDetail> findByText(String txt, Pageable pageable) {
		return detailRepository.findByText(txt, pageable);
	}

	public Page<ProductDetail> findByBrand(Integer id, Pageable pageable) {
		return detailRepository.findByBrand(new Brand(id), pageable);
	}

	public Page<ProductDetail> findByCategory(Integer catId, Pageable pageable) {
		return detailRepository.findByCategory(new Category(catId), pageable);
	}

	public Page<ProductDetail> findByPrice(Integer pFrom, Integer pTo, Pageable pageable) {
		return detailRepository.findByPrice(pFrom, pTo, pageable);
	}

	public Page<ProductDetail> findByColor(Integer colorId, Pageable pageable) {
		return detailRepository.findByColor(colorId, pageable);
	}
	// 未定
//	public Page<ProductDetail> findAllWithRankASC(Pageable pageable){
//		return detailRepository.findAllWithRankASC(pageable);
//	}

	public List<Color> findAllColors() {
		return utility.findAllColors();
	}

	public List<Brand> findAllBranchs() {
		return utility.findAllBrands();
	}

	public List<Category> findAllCategories() {
		return utility.findAllCategories();
	}

	public List<Size> findAllSizes() {
		return utility.findAllSizes();
	}
}
