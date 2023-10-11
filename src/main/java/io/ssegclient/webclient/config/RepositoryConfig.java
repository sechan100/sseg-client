package io.ssegclient.webclient.config;

import io.ssegclient.webclient.repository.JwtTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    
    
    /* JwtApiClient를 사용하여 refreshToken 요청을 전송하고 받아옴. 받아온 토큰을 사용하여 repository 빈을 생성한다. */
    @Bean
    JwtTokenRepository jwtTokenRepository(){
        return new JwtTokenRepository();
    }
}
