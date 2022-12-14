package com.cinshop.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Size;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class ProductUtilityTest {
	
	@Autowired
	private ProductUtility utility;
	
	@Test
	public void findAllColorsTest() {
		List<Color> colors = utility.findAllColors();
		assertThat(colors.size()).isGreaterThan(0);
	}
	
	@Test
	public void findAllBrandsTest() {
		List<Brand> brands = utility.findAllBrands();
		assertThat(brands.size()).isGreaterThan(0);
	}
	
	@Test
	public void findAllCategoriesTest() {
		List<Category> categories = utility.findAllCategories();
		assertThat(categories.size()).isGreaterThan(0);
	}
	
	@Test
	public void findAllSizesTest() {
		List<Size> sizes = utility.findAllSizes();
		assertThat(sizes.size()).isGreaterThan(0);
	}
//	@Test
//	public void addFavTest() {
//		utility.addFavProduct(1, 1);
//		utility.addFavProduct(1, 2);
//	}
//	
//	@Test
//	public void getFavProductsByCustIdTest() {
//		List<FavouriteProductDTO> favs = utility.getAllFavProductByCustId(1);
//		
//		favs.forEach(fav -> System.out.println(fav));
//		assertThat(favs.size()).isGreaterThan(0);
//	}
//	@Test
//	public void removeFavTest() {
//		utility.removeFavProduct(1, 1);
//	}

}
