package com.TCorp.FitNetServer.api.config;

import com.TCorp.FitNetServer.api.model.Role;
import com.TCorp.FitNetServer.api.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * File: ApplicationConfig
 * Author: turnernaef
 * Date: 10/30/23
 * Description:
 */

@Configuration
public class ApplicationConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            roleRepository.saveAll(List.of(
                    new Role("USER"),
                    new Role("ADMIN")
            ));
        };
    }
}
