package com.cinshop.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cinshop.common.entity.Customer;

@Repository
public class LoginUserRepository {
	
    private static final String SQL_FIND_BY_EMAIL = """
            SELECT
              u.e-mail AS email,
              CONCAT(u.first_name, u.lastname) AS user_name,
              u.password,
              u.role AS role_name
            FROM customer u
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
            roleList.add(rs.getString("role_name"));
        }
        if (email == null) {
            return null;
        }
        return new LoginUser(email, userName, password, roleList);
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<LoginUser> findCustomerByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email);
        LoginUser loginUser = namedParameterJdbcTemplate.query(SQL_FIND_BY_EMAIL, params, LOGIN_USER_EXTRACTOR);
        return Optional.ofNullable(loginUser);
    }
}
