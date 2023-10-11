package io.ssegclient.base.config;

import io.ssegclient.base.repository.JwtTokenRepository;
import io.ssegclient.webclient.client.JwtApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    
    /*
    * repository빈을 초기화 할 때, refresh token과 access token을 요청하여 세팅하고 초기화
    * */
    @Bean
    JwtTokenRepository jwtTokenRepository(JwtApiClient jwtApiClient){
        
        return new JwtTokenRepository(jwtApiClient);
    }
}
