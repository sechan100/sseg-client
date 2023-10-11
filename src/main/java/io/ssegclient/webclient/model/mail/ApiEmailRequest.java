package io.ssegclient.webclient.model.mail;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiEmailRequest {
    
    private EmailRequest emailRequest;
    
    private String templateName;
    
    private Map<String, Object> templateArgs;
    
}