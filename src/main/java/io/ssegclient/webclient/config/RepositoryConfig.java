package io.ssegclient.webclient.config;

import io.ssegclient.webclient.repository.JwtTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    
    
    @Bean
    JwtTokenRepository jwtTokenRepository(){
        return new JwtTokenRepository();
    }
}
