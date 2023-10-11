package io.ssegclient.webclient.repository;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenRepository {
    
    private String refreshToken;
    private String accessToken;
    
}
