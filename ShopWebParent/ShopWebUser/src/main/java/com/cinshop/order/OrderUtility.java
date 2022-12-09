package com.cinshop.order;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cinshop.common.entity.Tax;

@Component
public class OrderUtility {

	@Autowired
	private  JdbcTemplate template;

	public   Tax getCurrentTax() {
		String sql = "select * from tax order by effective_date asc limit 1";
		Tax tax = template.queryForObject(sql, new RowMapper<Tax>() {

			@Override
			public Tax mapRow(ResultSet rs, int rowNum) throws SQLException {
				Tax tax = new Tax();
				tax.setTax(rs.getInt("tax"));
				return tax;
			}

		});
		return tax;
	}
}
