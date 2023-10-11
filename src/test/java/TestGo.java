import io.ssegclient.base.config.ClientAppConfig;
import io.ssegclient.base.config.RepositoryConfig;
import io.ssegclient.base.constants.ClientAppProperties;
import io.ssegclient.base.http.ApiResponse;
import io.ssegclient.base.repository.JwtTokenRepository;
import io.ssegclient.webclient.client.SsegClientFacade;
import io.ssegclient.webclient.config.WebClientConfig;
import io.ssegclient.webclient.model.mail.ApiEmailRequest;
import io.ssegclient.webclient.model.mail.EmailRequest;
import io.ssegclient.webclient.model.mail.EmailResponse;
import model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = {ClientAppConfig.class, WebClientConfig.class, RepositoryConfig.class})
@EnableConfigurationProperties
public class TestGo {
    
    @Autowired
    private ClientAppProperties properties;
    
    @Autowired
    private JwtTokenRepository jwtTokenRepository;
    
    @Autowired
    private SsegClientFacade ssegClientFacade;
    
    @Test
    public void test() {
        ApiEmailRequest request = new ApiEmailRequest();
        
        EmailRequest emailRequest = new EmailRequest();
        
        emailRequest.setFrom("sechan");
        emailRequest.setTo("sechan100@gmail.com");
        emailRequest.setSubject("되냐?");
        request.setEmailRequest(emailRequest);
        
        request.setTemplateName("testTemplate");
        
        Map<String, Object> args = new HashMap<>();
        args.put("policy", "sechan");
        args.put("action", "sechan");
        args.put("msg", "sechan");
        args.put("comments", List.of(
                new Comment("1", "11111"),
                new Comment("2", "22222"),
                new Comment("3", "33333"),
                new Comment("4", "44444"),
                new Comment("5", "55555"),
                new Comment("6", "66666")
        ));
        
        request.setTemplateArgs(args);
        
        ApiResponse<EmailResponse> response = ssegClientFacade.sendMail(request);
        System.out.println(response);
    }
    
}
