package io.ssegclient.base.exception;

import io.ssegclient.base.http.ApiResponse;
import lombok.Getter;

@Getter
public class SsegApiResponseException extends RuntimeException {
    
    private final ApiResponse<?> response;
    
    public SsegApiResponseException(ApiResponse<?> response){
        super(response.getStatus() + response.getMsg());
        this.response = response;
    }
}
