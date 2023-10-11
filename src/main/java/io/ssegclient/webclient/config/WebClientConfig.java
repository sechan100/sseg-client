package io.ssegclient.webclient.config;

import io.ssegclient.base.constants.ClientAppProperties;
import io.ssegclient.base.repository.JwtTokenRepository;
import io.ssegclient.webclient.client.EmailApiClient;
import io.ssegclient.webclient.client.JwtApiClient;
import io.ssegclient.webclient.client.SsegClientFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
    
    @Bean
    public SsegClientFacade ssegClientFacade(ClientAppProperties properties, JwtTokenRepository repository, JwtApiClient jwtApiClient, EmailApiClient emailApiClient){
        return new SsegClientFacade(properties, repository, jwtApiClient, emailApiClient);
    }
    
    @Bean
    JwtApiClient jwtApiClient(WebClient.Builder webClientBuilder, ClientAppProperties appProperties){
        return new JwtApiClient(webClientBuilder, appProperties);
    }
    
    @Bean
    EmailApiClient emailApiClient(WebClient.Builder webClientBuilder, ClientAppProperties appProperties){
        return new EmailApiClient(webClientBuilder, appProperties);
    }
}
