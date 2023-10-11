package io.ssegclient.webclient.model;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    
    private String appId;
    private String appSecret;
    
    
    public RefreshTokenRequest(String appId, String appSecret){
        this.appId = appId;
        this.appSecret = appSecret;
    }
}
