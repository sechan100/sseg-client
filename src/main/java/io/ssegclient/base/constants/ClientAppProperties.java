package io.ssegclient.base.constants;

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
        private String baseUrl;
        private String refreshTokenUrl;
        private String accessTokenUrl;
        private String sendEmailUrl;
    }
}
