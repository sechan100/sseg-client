package io.ssegclient.webclient;

import io.ssegclient.core.ClientAppProperties;
import io.ssegclient.exception.ApiCallFailureException;
import io.ssegclient.webclient.model.ApiResponse;
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
    
    
    public String requestGetRefreshToke(){
        
        // appId, appSecret로 request 객체 구성
        RefreshTokenRequest request = new RefreshTokenRequest(appProperties.getAppId(), appProperties.getAppSecret());
        
        // api 요청 보내고 리프레시 토큰 받기
        ApiResponse response = webClient.get()
                .uri(appProperties.getSite().getRefreshTokenUrl())
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block();
        
        if(response == null){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버로부터 응답이 없습니다. 서버에 문제가 생겼거나, 네트워크 상태가 좋지 않을 수 있습니다.");
        }
        
        // 리프레시 토큰 반환
        if(response.isSuccess()){
            return (String) response.getData();
            
        } else {
            throw new ApiCallFailureException(response);
        }
        
        
    }
    
    
    
    
    
    
    public void someMethod() {
        
        // GET 요청
        String response = webClient.get()
                .uri("http://example.com/api/resource")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
//        // POST 요청
//        MyRequest myRequest = new MyRequest("data1", "data2");
//        MyResponse myResponse = webClient.post()
//                .uri("http://example.com/api/resource")
//                .body(Mono.just(myRequest), MyRequest.class)
//                .retrieve()
//                .bodyToMono(MyResponse.class)
//                .block();
    }
}
