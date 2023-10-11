package io.ssegclient.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "sseg")
public class ClientAppProperties {
    
    private String appId;
    private String appSecret;
    private Site site;
    
    
    @Data
    public static class Site {
        private String host;
        private String refreshTokenUrl;
        private String accessTokenUrl;
    }
}
