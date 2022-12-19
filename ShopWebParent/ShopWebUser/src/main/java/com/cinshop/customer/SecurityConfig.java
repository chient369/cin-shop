package com.cinshop.customer;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
        		
        		//ログイン情報の送信先URL
                .loginProcessingUrl("/login")
                
                //ログインページ
                .loginPage("/login")
                
                //ログイン後の遷移する画面
                .defaultSuccessUrl("/testDebug", true)
                
                //ログインエラー用のURL
                .failureUrl("/login?error")

                //ログインページは誰でも閲覧可能
                .permitAll(true)
        ).logout(logout -> logout
        		
        		//ログアウトするURL
        		.logoutUrl("/logout")
        		
        		//ログアウト後の遷移する画面
                .logoutSuccessUrl("/login")
                
        ).authorizeHttpRequests(authz -> authz
        		
        		//静的ファイルはログイン無しでもアクセス可能
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                
                //ログイン無しでもアクセス可能
                .requestMatchers("/",
                				 "/register",
                				 "/fragments/**").permitAll()
                
                //権限ごとにアクセス可能なURL
                .requestMatchers("/testDebug", 
                				 "/logout").hasRole("USER")
                
                //他のURLはログイン後のみアクセス可能
                .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}