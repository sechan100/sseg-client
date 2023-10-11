package io.ssegclient.base.config;

import io.ssegclient.base.constants.ClientAppProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientAppConfig {
    
    @Bean
    public ClientAppProperties clientAppProperties(){
        return new ClientAppProperties();
    }
    
}
