package com.security.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .requestMatchers("/myAccount/**", "myBalance", "myCards", "myLoans")
            .authenticated()
            .requestMatchers("notices", "contact")
            .permitAll()
            .and().formLogin()
            .and().httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // UserDetails admin = User.withDefaultPasswordEncoder()
        //     .username("admin")
        //     .password("12345")
        //     .authorities("admin")
        //     .build();

        // UserDetails user = User.withDefaultPasswordEncoder()
        //     .username("user")
        //     .password("12345")
        //     .authorities("read")
        //     .build();

        // return new InMemoryUserDetailsManager(admin, user);

        // create bean of PasswordEncoder instead

        UserDetails admin = User
            .withUsername("admin")
            .password("12345")
            .authorities("admin")
            .build();

        UserDetails user = User
            .withUsername("user")
            .password("12345")
            .authorities("read")
            .build();

        return new InMemoryUserDetailsManager(admin, user);

    }

    /**
     * NoOpPasswordEncoder not recommended for production purpose
     * @return
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
}
