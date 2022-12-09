//package com.cinshop.product;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.stereotype.Repository;
//
//import com.cinshop.common.entity.Color;
//import com.cinshop.common.entity.Product;
//import com.cinshop.common.entity.ProductDetail;
//import com.cinshop.common.entity.Size;
//
//@Repository
//public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
//	
//	List<Product> findById(Integer id);
//	
//	List<Product> findAllSizesOfProduct(Integer id);
//	
//	Page<ProductDetail> findBySize(Size size, Pageable pageable);
//	
//	Page<ProductDetail> findByColor(Color color, Pageable pageable);
//}
