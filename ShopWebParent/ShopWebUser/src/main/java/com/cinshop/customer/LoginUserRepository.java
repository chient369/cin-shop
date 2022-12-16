package com.cinshop.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;

@Repository
public class LoginUserRepository {
	
    @Autowired
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
    private static final String SQL_FIND_BY_EMAIL = """
            SELECT
              email,
              concat(first_name, last_name) AS user_name,
              password
            FROM customer WHERE email = :email
            """;

    private static final ResultSetExtractor<LoginUser> LOGIN_USER_EXTRACTOR = (rs) -> {
        String email = null;
        String userName = null;
        String password = null;
        List<String> roleList = new ArrayList<>();
        while (rs.next()) {
            if (email == null) {
                email = rs.getString("email");
                userName = rs.getString("user_name");
                password = rs.getString("password");
            }
            roleList.add("ROLE_USER");
        }
        if (email == null) {
            return null;
        }
        System.out.println(email +  " " + userName +  " " + password + " " + roleList.get(0));
        return new LoginUser(email, userName, password, roleList);
    };

    public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<LoginUser> findCustomerByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email);
        LoginUser loginUser = namedParameterJdbcTemplate.query(SQL_FIND_BY_EMAIL, params, LOGIN_USER_EXTRACTOR);
        return Optional.ofNullable(loginUser);
    }
}
