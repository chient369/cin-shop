package com.cinshop.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.Brand;
import com.cinshop.common.entity.Category;
import com.cinshop.common.entity.Color;
import com.cinshop.common.entity.Customer;
import com.cinshop.common.entity.FavouriteProduct;
import com.cinshop.common.entity.Size;
import com.cinshop.dto.FavouriteProductDTO;

@Component
public class ProductUtility {

	@Autowired
	private JdbcTemplate template;

	public List<Color> findAllColors() {
		String sql = "SELECT * FROM COLOR";
		List<Color> colors = template.query(sql, new RowMapper<Color>() {

			@Override
			public Color mapRow(ResultSet rs, int rowNum) throws SQLException {
				Color color = new Color();
				color.setId(rs.getInt("color_id"));
				color.setName(rs.getString("name"));
				color.setColorCode(rs.getString("color_code"));
				return color;
			}

		});
		return colors;
	}

	public List<Brand> findAllBrands() {
		String sql = "SELECT * FROM BRAND";
		List<Brand> brands = template.query(sql, new RowMapper<Brand>() {

			@Override
			public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
				Brand brand = new Brand();
				brand.setId(rs.getInt("brand_id"));
				brand.setName(rs.getString("name"));
				brand.setBrandLogo(rs.getString("brand_logo"));
				return brand;
			}

		});
		return brands;
	}

	public List<Category> findAllCategories() {
		String sql = "SELECT * FROM CATEGORY";
		List<Category> categories = template.query(sql, new RowMapper<Category>() {

			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getString("name"));
				return category;
			}

		});
		return categories;
	}

	public List<Size> findAllSizes() {
		String sql = "SELECT * FROM SIZE";
		List<Size> sizes = template.query(sql, new RowMapper<Size>() {

			@Override
			public Size mapRow(ResultSet rs, int rowNum) throws SQLException {
				Size size = new Size();
				size.setId(rs.getInt("size_id"));
				size.setSize(rs.getFloat("size"));
				return size;
			}

		});
		return sizes;
	}

	
}
