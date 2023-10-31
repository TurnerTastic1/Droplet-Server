package com.TCorp.FitNetServer.api.config;

import com.TCorp.FitNetServer.api.model.UserAccount;
import com.TCorp.FitNetServer.api.repository.UserAccountRepository;
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
    CommandLineRunner commandLineRunner(UserAccountRepository UserAccountRepo) {
        return args -> {
             UserAccount john = new UserAccount(
                     1L,
                     "John",
                     "John@gmail",
                     "password",
                     "standard"

             );

             UserAccount alex = new UserAccount(
                     2L,
                     "Alex",
                     "Alex@gmail",
                     "password",
                     "standard"
             );

             UserAccountRepo.saveAll(
                     List.of(john, alex)
             );
        };
    }
}
