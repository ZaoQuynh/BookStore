package com.example.BookStore.builder;


import com.example.BookStore.entity.User;
import com.example.BookStore.repos.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(userRepository.findByUsernameAndIsDeletedFalse("admin").isEmpty()){

                User user = User.builder()
                        .username("admin")
                        .fullName("admin")
                        .email("admin@gmail.com")
                        .isEnabled(true)
                        .password(passwordEncoder.encode("admin"))
                        .gender("Nam")
                        .role("ADMIN")
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
        };
    }
}
