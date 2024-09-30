package za.ac.cput.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import za.ac.cput.filter.JwtRequestFilter;

// Configuration for Spring Security Web Security

@Configuration
@EnableWebSecurity // Enable Spring Security
public class SecurityConfig extends WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private  JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }






}