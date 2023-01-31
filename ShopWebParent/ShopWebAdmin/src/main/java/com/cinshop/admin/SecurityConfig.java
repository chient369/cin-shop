package com.cinshop.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	 InMemoryUserDetailsManager userDetailsService() {
	    UserDetails admin = User.withUsername("admin")
	        .password(passwordEncoder().encode("cinshopadmin"))
	        .roles("ADMIN")
	        .build();
	  
	    return new InMemoryUserDetailsManager(admin);
	}
	

	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login

				// ログイン情報の送信先URL
				.loginProcessingUrl("/login")

				// ログインページ
				.loginPage("/login")

				// ログイン後の遷移する画面
				.defaultSuccessUrl("/", true)

				// ログインエラー用のURL
				.failureUrl("/login?error")

				// ログインページは誰でも閲覧可能
				.permitAll()).logout(logout -> logout

						// ログアウトするURL
						.logoutUrl("/logout")

						// ログアウト後の遷移する画面
					.logoutSuccessUrl("/login")

		).authorizeHttpRequests(authz -> authz

				// 静的ファイルはログイン無しでもアクセス可能
				.requestMatchers("/css/**", "/js/**", "/lib/**", "/img/**").permitAll()

				// ログイン無しでもアクセス可能
				// .requestMatchers("/**").permitAll()
				// 権限ごとにアクセス可能なURL

				// 他のURLはログイン後のみアクセス可能
				.anyRequest().authenticated()).csrf().disable();
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String rawPassword = "admincinshop2022";
//        String encodedPassword = encoder.encode(rawPassword);
//         
//        System.out.println(encodedPassword);
//    }
}