package com.pranoy.spring.security.security.config;

import com.pranoy.spring.security.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/couponapi/coupons/{code:^[A-Z]*$}", "/", "index",
                        "/showCreateCoupon", "/createCoupon", "/createResponse", "createResponse", "/showGetCoupon",
                        "/getCoupon", "/couponDetails").hasAnyRole("USER", "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/couponapi/coupons", "/saveCoupon", "/getCoupon").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .csrf().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
