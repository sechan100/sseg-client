package io.ssegclient.webclient.client;

import io.ssegclient.base.constants.ClientAppProperties;
import io.ssegclient.base.exception.SsegApiResponseException;
import io.ssegclient.base.http.ApiResponse;
import io.ssegclient.webclient.model.mail.ApiEmailRequest;
import io.ssegclient.webclient.model.mail.EmailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;

public class EmailApiClient {
    
    private final ClientAppProperties properties;
    private final WebClient.Builder webClientBuilder;
    
    public EmailApiClient(WebClient.Builder webClientBuilder, ClientAppProperties appProperties){
        this.webClientBuilder = webClientBuilder;
        this.properties = appProperties;
    }
    
    
    /*
    * access token을 받아서 webClient의 기본 header에 추가하고 webClient를 빌드.
    * */
    public WebClient getWebClient(String accessToken){
        return webClientBuilder
                .defaultHeader("Authorization", "Bearer " + accessToken)
                .build();
    }
    
    public ApiResponse<EmailResponse> requestSendMail(ApiEmailRequest request, String accessToken){
        
        ApiResponse response = getWebClient(accessToken)
                .post()
                .uri(properties.getSite().getSendEmailUrl())
                .bodyValue(request)
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
        } else {
            return response;
        }
    }
    
    
}
