package com.security.springsecuritydemo.config;


import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.security.springsecuritydemo.filter.CsrfCookieFilter;
import com.security.springsecuritydemo.filter.JwtTokenGeneratorFilter;
import com.security.springsecuritydemo.filter.JwtTokenValidatorFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        // http.securityContext().requireExplicitSave(false)
        //     .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))        // These 2 lines supply our external angular application with JsessionId so that we dont have to provide credentials every time earlier we were using spring inbuilt login so didnt needed this 
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)     // To tell UI that dont create JSESSIONID I will create my own JWT token
            .and()
            .cors().configurationSource(
                new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));       // because an authorization header will be sent from UI to backends
                        config.setMaxAge(3600L);
                        return config;
                    }
                }
            ).and()
            .csrf(
                (csrf)-> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register", "/addAccountDetails")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests()
            // .requestMatchers("/myCards").hasAuthority("admin")
            // .requestMatchers("/myAccount/**", "myBalance", "myLoans").hasAnyAuthority("user", "admin")
            .requestMatchers("/myCards").hasRole("ADMIN")
            .requestMatchers("/myAccount/**", "myBalance", "myLoans").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/user").authenticated()
            .requestMatchers("/notices", "/contact", "/register", "/addAccountDetails")
            .permitAll()
            .and().formLogin()
            .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // for inbuilt users table schema and authorities table
    // @Bean
    // public UserDetailsService userDetailsService(DataSource dataSource){
        // return new JdbcUserDetailsManager(dataSource);
    // }

    // to create in memory users
    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    //     // UserDetails admin = User.withDefaultPasswordEncoder()
    //     //     .username("admin")
    //     //     .password("12345")
    //     //     .authorities("admin")
    //     //     .build();

    //     // UserDetails user = User.withDefaultPasswordEncoder()
    //     //     .username("user")
    //     //     .password("12345")
    //     //     .authorities("read")
    //     //     .build();

    //     // return new InMemoryUserDetailsManager(admin, user);

    //     // create bean of PasswordEncoder instead

    //     UserDetails admin = User
    //         .withUsername("admin")
    //         .password("12345")
    //         .authorities("admin")
    //         .build();

    //     UserDetails user = User
    //         .withUsername("user")
    //         .password("12345")
    //         .authorities("read")
    //         .build();

    //     return new InMemoryUserDetailsManager(admin, user);

    // }

    // /**
    //  * NoOpPasswordEncoder not recommended for production purpose
    //  * if no password encoder needed
    //  * @return
    //  */

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }
    
}
