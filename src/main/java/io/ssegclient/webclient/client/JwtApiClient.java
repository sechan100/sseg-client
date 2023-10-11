package io.ssegclient.webclient.client;

import io.ssegclient.base.constants.ClientAppProperties;
import io.ssegclient.base.exception.SsegApiResponseException;
import io.ssegclient.base.http.ApiResponse;
import io.ssegclient.webclient.model.RefreshTokenRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

public class JwtApiClient {
    
    private final WebClient webClient;
    private final ClientAppProperties appProperties;

    public JwtApiClient(WebClient.Builder webClientBuilder, ClientAppProperties appProperties) {
        this.webClient = webClientBuilder.build();
        this.appProperties = appProperties;
    }
    
    
    public String requestGetRefreshToken(){
        
        // appId, appSecret로 request 객체 구성
        RefreshTokenRequest request = new RefreshTokenRequest(appProperties.getAppId(), appProperties.getAppSecret());
        
        // api 요청 보내고 리프레시 토큰 받기
        ApiResponse response = webClient.get()
                .uri(appProperties.getSite().getRefreshTokenUrl())
                .retrieve()
                .onStatus(
                        status -> !status.equals(HttpStatus.OK),
                        clientResponse -> clientResponse.bodyToMono(ApiResponse.class)
                                .map(SsegApiResponseException::new)
                )
                .bodyToMono(ApiResponse.class)
                .block();
        
        if(response == null){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버로부터 응답이 없습니다. 서버에 문제가 생겼거나, 네트워크 상태가 좋지 않을 수 있습니다.");
        }
        
        // 리프레시 토큰 반환
        if(response.isSuccess()){
            return (String) response.getData();
            
        } else {
            throw new SsegApiResponseException(response);
        }
        
        
    }
    
    public String requestGetAccessToken(String refreshToken) throws SsegApiResponseException {
        
        // api 요청 보내고 액세스 토큰 받기
        // 리프레시 토큰으로 request 구성: 헤더에 'Authorization: Bearer {리프레시 토큰}' 추가.
        ApiResponse response = webClient.get()
                .uri(appProperties.getSite().getAccessTokenUrl())
                .header("Authorization", "Bearer " + refreshToken)
                .retrieve()
                .onStatus(
                        status -> !status.equals(HttpStatus.OK),
                        clientResponse -> clientResponse.bodyToMono(ApiResponse.class)
                                .map(SsegApiResponseException::new)
                )
                .bodyToMono(ApiResponse.class)
                .block();
        
        if(response == null){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버로부터 응답이 없습니다. 서버에 문제가 생겼거나, 네트워크 상태가 좋지 않을 수 있습니다.");
        }
        
        // 액세스 토큰 반환
        if(response.isSuccess()){
            return (String) response.getData();
            
        } else {
            throw new SsegApiResponseException(response);
        }
        
    }
    
}
