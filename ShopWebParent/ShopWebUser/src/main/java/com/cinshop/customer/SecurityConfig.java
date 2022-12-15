package com.cinshop.customer;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login
        		
        		//ログイン情報の送信先URL
                .loginProcessingUrl("/login")
                
                //ログインページ
                .loginPage("/login")
                
                //ログイン後の遷移する画面
                .defaultSuccessUrl("/about")
                
                //ログインエラー用のURL
                .failureUrl("/login?error")
                
                //ログイン画面は誰でもアクセス可能
                .permitAll()
        ).logout(logout -> logout
        		
        		//ログアウト後の遷移する画面
                .logoutSuccessUrl("/login")
                
        ).authorizeHttpRequests(authz -> authz
        		
        		//静的ファイルはログイン無しでもアクセス可能
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                
                //ログイン無しでもアクセス可能
                //.requestMatchers("/").permitAll()
                
                //権限ごとにアクセス可能なURL
                .requestMatchers("/about").hasRole("USER")
                
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