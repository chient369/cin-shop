package com.cinshop.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavoriteProduct;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;

@Service
public class FavoriteProductService {

	@Autowired
	private FavoriteProductRepository repository;

	public FavoriteProduct addFavProduct(Integer custId, Integer product_id) {
		FavoriteProduct favP = new FavoriteProduct(new ProductDetail(product_id), new Customer(custId));
		return repository.save(favP);
	}

	public void removeFavProduct(Integer custId, Integer product_id) {
		FavoriteProduct favP = new FavoriteProduct(new ProductDetail(product_id), new Customer(custId));
		repository.delete(favP);
	}

	public List<ProductDetail> findAllByCustomerId(Integer custId) {
		List<FavoriteProduct> fav = repository.findByCustomer(new Customer(custId));
		List<ProductDetail> products = new ArrayList<>();

		Iterator<FavoriteProduct> it = fav.iterator();
		while (it.hasNext()) {
			products.add(it.next().getProductDetail());
		}

		return products;
	}
}
