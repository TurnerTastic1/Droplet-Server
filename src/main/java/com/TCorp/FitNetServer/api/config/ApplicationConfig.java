package com.TCorp.FitNetServer.api.config;

import com.TCorp.FitNetServer.api.model.Role;
//import com.TCorp.FitNetServer.api.repository.RoleRepository;
import com.TCorp.FitNetServer.api.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * File: ApplicationConfig
 * Author: turnernaef
 * Date: 10/30/23
 * Description:
 */

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserEntityRepository userEntityRepository;
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userEntityRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
//        return args -> {
//            roleRepository.saveAll(List.of(
//                    new Role("USER"),
//                    new Role("ADMIN")
//            ));
//        };
//    }
}
