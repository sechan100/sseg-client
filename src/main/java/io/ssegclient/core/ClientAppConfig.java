package io.ssegclient.core;

import io.ssegclient.webclient.JwtApiClient;
import io.ssegclient.webclient.repository.JwtTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientAppConfig {
    
    @Bean
    public ClientAppProperties clientAppProperties(){
        return new ClientAppProperties();
    }
    
    @Bean
    public SsegClient ssegClient(ClientAppProperties properties, JwtTokenRepository repository, JwtApiClient jwtApiClient){
        return new SsegClient(properties, repository, jwtApiClient);
    }
}
