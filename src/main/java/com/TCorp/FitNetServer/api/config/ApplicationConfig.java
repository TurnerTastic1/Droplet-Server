package com.TCorp.FitNetServer.api.config;

import com.TCorp.FitNetServer.api.model.Role;
import com.TCorp.FitNetServer.api.repository.RoleRepository;
import com.TCorp.FitNetServer.api.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
