package com.cinshop.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavouriteProduct;
import com.cinshop.common.entity.Product;

@Service
public class FavouriteProductService {

	@Autowired
	private FavouriteProductRepository repository;

	public FavouriteProduct addFavProduct(Integer custId, Integer product_id) {
		FavouriteProduct favP = new FavouriteProduct(new Product(product_id), new Customer(custId));
		return repository.save(favP);
	}

	public void removeFavProduct(Integer custId, Integer product_id) {
		FavouriteProduct favP = new FavouriteProduct(new Product(product_id), new Customer(custId));
		repository.delete(favP);
	}

	public List<Product> findAllByCustomerId(Integer custId) {
		List<FavouriteProduct> fav = repository.findByCustomer(new Customer(custId));
		List<Product> products = new ArrayList<>();

		Iterator<FavouriteProduct> it = fav.iterator();
		while (it.hasNext()) {
			products.add(it.next().getProduct());
		}

		return products;
	}
}
