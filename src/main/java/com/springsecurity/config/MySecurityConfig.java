package com.springsecurity.config;

import com.springsecurity.services.CuatomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CuatomUserDetailService cuatomUserDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                // .disable()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()

                    .antMatchers("/home","/signin","/register").permitAll()
                 // or u can write
                    .antMatchers("/public/**").hasRole("NORMAL")
                    .antMatchers("/users/**").hasRole("ADMIN")
                   // .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/users/")
        ;
                // .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cuatomUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }
}
