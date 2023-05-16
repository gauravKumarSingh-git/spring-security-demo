package com.security.springsecuritydemo.config;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(
                new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }
            ).and()
            // .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/myAccount/**", "myBalance", "myCards", "myLoans")
            .authenticated()
            .requestMatchers("/notices", "/contact", "/register")
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
