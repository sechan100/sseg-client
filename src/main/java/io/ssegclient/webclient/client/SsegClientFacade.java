package io.ssegclient.webclient.client;


import io.ssegclient.base.constants.ClientAppProperties;
import io.ssegclient.base.constants.SsegApiResponseStatus;
import io.ssegclient.base.exception.SsegApiResponseException;
import io.ssegclient.base.http.ApiResponse;
import io.ssegclient.base.repository.JwtTokenRepository;
import io.ssegclient.webclient.model.mail.ApiEmailRequest;
import io.ssegclient.webclient.model.mail.EmailResponse;


public class SsegClientFacade {
    
    private final ClientAppProperties properties;
    private final JwtTokenRepository repository;
    private final JwtApiClient jwtApiClient;
    private final EmailApiClient emailApiClient;
    

    public SsegClientFacade(ClientAppProperties properties, JwtTokenRepository repository, JwtApiClient jwtApiClient, EmailApiClient emailApiClient) {
        this.properties = properties;
        this.repository = repository;
        this.jwtApiClient = jwtApiClient;
        this.emailApiClient = emailApiClient;
    }
    
    
    
    
    public ApiResponse<EmailResponse> sendMail(ApiEmailRequest request) throws SsegApiResponseException {
        
        // request 객체를 json으로 직렬화하여 api를 요청
        ApiResponse<EmailResponse> response = emailApiClient.requestSendMail(request, repository.getAccessToken());
        
        // 만약, response의 에러가 TOKEN_EXPIRED라면, access token을 갱신하고 다시 요청
        if(response.getStatus() == SsegApiResponseStatus.TOKEN_EXPIRED){
            repository.renewAccessToken();
            response = emailApiClient.requestSendMail(request, repository.getAccessToken());
        }
        
        // 값을 받아오는데 성공했다면, response를 리턴
        if(response.getStatus() == SsegApiResponseStatus.SUCCESS){
            return response;
            
        // 성공이 아니라면 예외를 발생 -> api를 호출한 코드에 처리를 위임
        } else {
            throw new SsegApiResponseException(response);
        }
        
    }
    
    
    
    
    
}
