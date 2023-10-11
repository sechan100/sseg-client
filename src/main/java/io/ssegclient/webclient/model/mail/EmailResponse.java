package io.ssegclient.webclient.model.mail;

import io.ssegclient.webclient.constants.EmailEnums;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmailResponse {
    
    private final String requestId;
    private final EmailEnums.Status status;
    private final String msg;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String from;
    private final String to;
    private final String subject;
    private final EmailEnums.ErrorCode errorCode;
    
}
