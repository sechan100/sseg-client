import io.ssegclient.base.config.ClientAppConfig;
import io.ssegclient.base.config.RepositoryConfig;
import io.ssegclient.base.constants.ClientAppProperties;
import io.ssegclient.base.http.ApiResponse;
import io.ssegclient.base.repository.JwtTokenRepository;
import io.ssegclient.webclient.client.SsegClientFacade;
import io.ssegclient.webclient.config.WebClientConfig;
import io.ssegclient.webclient.model.mail.ApiEmailRequestBuilder;
import io.ssegclient.webclient.model.mail.EmailResponse;
import model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

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
    public void test() throws InterruptedException {
        ApiEmailRequestBuilder builder = new ApiEmailRequestBuilder();
        
        builder.setFrom("sechan");
        builder.setTo("sechan100@gmail.com");
        builder.setSubject("되냐?");
        
        builder.setTemplateName("testTemplate");
        
        builder.addArg("policy", "sechan");
        builder.addArg("action", "sechan");
        builder.addArg("msg", "sechan");
        builder.addArg("comments", List.of(
                new Comment("1", "11111"),
                new Comment("2", "22222"),
                new Comment("3", "33333"),
                new Comment("4", "44444"),
                new Comment("5", "55555"),
                new Comment("6", "66666")
        ));
        
        ApiResponse<EmailResponse> response = ssegClientFacade.sendMail(builder.build());
        System.out.println(response);
        Thread.sleep(1000L * 40);
        ApiResponse<EmailResponse> response2 = ssegClientFacade.sendMail(builder.build());
        System.out.println(response2);
    }
    
}
