package io.ssegclient.webclient.model;

import io.ssegclient.constants.SsegApiResponseStatus;
import lombok.Data;

@Data
public class ApiResponse<T> {
    
    private T data;
    
    SsegApiResponseStatus status;
    
    private String msg;
 
    
    
    public boolean isStatus(SsegApiResponseStatus status){
        return this.status == status;
    }
    
    public boolean isSuccess(){
        return this.status == SsegApiResponseStatus.SUCCESS;
    }
}