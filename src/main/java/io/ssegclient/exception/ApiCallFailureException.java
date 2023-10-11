package io.ssegclient.exception;

import io.ssegclient.webclient.model.ApiResponse;
import lombok.Getter;

@Getter
public class ApiCallFailureException extends RuntimeException {
    
    private final ApiResponse<Object> response;
    
    public ApiCallFailureException(ApiResponse<Object> response){
        super(response.getMsg());
        this.response = response;
    }
    
}
