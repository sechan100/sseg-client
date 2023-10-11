package io.ssegclient.base.exception;

import io.ssegclient.base.http.ApiResponse;
import lombok.Getter;

@Getter
public class SsegApiResponseException extends RuntimeException {
    
    private final ApiResponse<?> response;
    
    public SsegApiResponseException(ApiResponse<?> response){
        super("\n========================================================\n" +
                "STATUS: " + response.getStatus() + "\n" +
                "MSG: " + response.getMsg() +
              "\n========================================================");
        this.response = response;
    }
}
