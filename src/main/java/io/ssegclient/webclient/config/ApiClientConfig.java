package io.ssegclient.webclient.config;


import io.ssegclient.core.ClientAppProperties;
import io.ssegclient.webclient.JwtApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiClientConfig {
    
    @Bean
    JwtApiClient jwtApiClient(WebClient.Builder webClientBuilder, ClientAppProperties appProperties){
        return new JwtApiClient(webClientBuilder, appProperties);
    }
}
