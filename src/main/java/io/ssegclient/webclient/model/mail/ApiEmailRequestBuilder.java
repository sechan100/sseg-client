package io.ssegclient.webclient.model.mail;


import java.util.HashMap;
import java.util.Map;

public class ApiEmailRequestBuilder {
    
    private EmailRequest emailRequest;
    private String templateName;
    private Map<String, Object> args;
    
    public ApiEmailRequestBuilder() {
        this.emailRequest = new EmailRequest();
        this.args = new HashMap<>();
    }
    
    
    public ApiEmailRequestBuilder setFrom(String from) {
        emailRequest.setFrom(from);
        return this;
    }
    
    public ApiEmailRequestBuilder setTo(String to) {
        emailRequest.setTo(to);
        return this;
    }
    
    public ApiEmailRequestBuilder setSubject(String subject) {
        emailRequest.setSubject(subject);
        return this;
    }
    
    
    
    public ApiEmailRequestBuilder setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }
    
    public ApiEmailRequestBuilder addArg(String key, Object value) {
        args.put(key, value);
        return this;
    }
    
    public ApiEmailRequestBuilder setArgs(Map<String, Object> args) {
        this.args = args;
        return this;
    }
    
    public ApiEmailRequest build() {
        return new ApiEmailRequest(emailRequest, templateName, args);
    }
}
