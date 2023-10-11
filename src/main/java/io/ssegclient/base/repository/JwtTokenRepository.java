package io.ssegclient.base.repository;


import io.ssegclient.base.constants.SsegApiResponseStatus;
import io.ssegclient.base.exception.SsegApiResponseException;
import io.ssegclient.webclient.client.JwtApiClient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenRepository {
    
    private String refreshToken;
    private String accessToken;
    private final JwtApiClient jwtApiClient;
    
    public JwtTokenRepository(JwtApiClient jwtApiClient) {
        this.jwtApiClient = jwtApiClient;
        
        // refresh token과 access token을 요청하여 세팅
        this.refreshToken = jwtApiClient.requestGetRefreshToken();
        this.accessToken = jwtApiClient.requestGetAccessToken(refreshToken);
    }
    
    // access token 갱신: refresh token으로 access token을 요청하여 repository에 저장까지 함
    public void renewAccessToken(){
        
        try {
            this.accessToken = jwtApiClient.requestGetAccessToken(refreshToken);
            
        // refresh token이 만료되었다면, refresh token을 갱신하고 다시 access token을 요청
        } catch (SsegApiResponseException e) {
            
            SsegApiResponseStatus status = e.getResponse().getStatus();
            if(status == SsegApiResponseStatus.TOKEN_EXPIRED){
                renewRefreshToken();
                this.accessToken = jwtApiClient.requestGetAccessToken(refreshToken);
            } else {
                throw e;
            }
        }
    }
    
    private void renewRefreshToken(){
        this.refreshToken = jwtApiClient.requestGetRefreshToken();
    }
}
