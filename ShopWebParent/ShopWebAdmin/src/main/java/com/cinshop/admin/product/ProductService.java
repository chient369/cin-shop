package com.cinshop.admin.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cinshop.admin.utility.FileUploadUtil;
import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Product;
import com.cinshop.common.entity.ProductDetail;
import com.cinshop.common.entity.ProductImage;
import com.cinshop.common.entity.Size;

@Service
public class ProductService {
	private static final String IMAGE_PATH = "/product-images/";

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductUtility utility;

	public Page<ProductDetail> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Page<ProductDetail> findByText(String txt, Pageable pageable) {
		return productRepository.findByText(txt, pageable);
	}

	public Page<ProductDetail> findByBrandOrCategory(Integer brandId, Integer catId, Pageable pageable) {
		return productRepository.findByBrandOrCategory(brandId, catId, pageable);
	}

	public List<Brand> findAllBrands() {
		return utility.findAllBrands();
	}

	public ProductDetail createNewProductDetail(ProductDetail detail, MultipartFile mainImage,
			MultipartFile[] extraImages) throws IOException {
		String mainImageName = IMAGE_PATH + FileUploadUtil.saveMainImage(mainImage);
		detail.setMainImage(mainImageName);
		ProductDetail savedDetail = productRepository.save(detail);
		List<String> extraImageNames = FileUploadUtil.saveExtraImage(detail.getId(), extraImages);

		ProductDetail updatedDetail = updateProductDetailImage(savedDetail, mainImageName, extraImageNames);

		return updatedDetail;
	}

	private ProductDetail updateProductDetailImage(ProductDetail detail, String mainImage, List<String> extraImages) {
		if (mainImage == null)
			mainImage = detail.getMainImage();

		List<ProductImage> images = new ArrayList<>();
		Iterator<String> it = extraImages.iterator();

		while (it.hasNext()) {
			String imageName = it.next();

			String imagePath = IMAGE_PATH + imageName;
			ProductImage image = new ProductImage(imagePath, detail);

			images.add(image);
		}

		detail.setImages(images);

		return productRepository.save(detail);
	}

	/* ========================================== */

	public ProductDetail addNewProduct(Integer colorId, Integer sizeId, Integer stockAmount, Integer detailId) {
		ProductDetail DBProduct = findById(detailId);
		Product newItem = new Product(DBProduct, new Color(colorId), new Size(sizeId), stockAmount);
		DBProduct.getProducts().add(newItem);
		ProductDetail savedDetail = updateDetail(DBProduct);
		return savedDetail;
	}
	public ProductDetail deleteProduct(Integer detailId, Integer productId) {
		ProductDetail DBProduct = findById(detailId);
		Set<Product> products = DBProduct.getProducts();
		
		products.remove(new Product(productId));
		
		
		ProductDetail savedDetail = updateDetail(DBProduct);
		return savedDetail;
	}

	public List<Category> findAllCategories() {
		return utility.findAllCategories();
	}

	public List<Color> findAllColors() {
		return utility.findAllColors();

	}

	public List<Size> findAllSizes() {
		return utility.findAllSizes();
	}

	public ProductDetail findById(Integer pId) {
		return productRepository.findById(pId).get();
	}

	public ProductDetail updateDetail(ProductDetail productDetail) {
		ProductDetail dbDetail = productRepository.findById(productDetail.getId()).get();
		if (productDetail.getProducts().size() == 0) {
			productDetail.setProducts(dbDetail.getProducts());
		}
		if (productDetail.getImages().size() == 0)
			productDetail.setImages(dbDetail.getImages());
		if (productDetail.getReviews().size() == 0)
			productDetail.setReviews(dbDetail.getReviews());
		return productRepository.save(productDetail);
	}
}
