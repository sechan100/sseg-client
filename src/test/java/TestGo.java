import io.ssegclient.core.ClientAppConfig;
import io.ssegclient.core.ClientAppProperties;
import io.ssegclient.webclient.JwtApiClient;
import io.ssegclient.webclient.config.ApiClientConfig;
import io.ssegclient.webclient.config.RepositoryConfig;
import io.ssegclient.webclient.config.WebClientConfig;
import io.ssegclient.webclient.repository.JwtTokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = {ClientAppConfig.class, ApiClientConfig.class, WebClientConfig.class, RepositoryConfig.class})
@EnableConfigurationProperties
public class TestGo {
    
    @Autowired
    private ClientAppProperties properties;
    
    @Autowired
    private JwtTokenRepository jwtTokenRepository;
    
    @Autowired
    private JwtApiClient jwtApiClient;
    
    @Test
    public void test() {
        String refreshToken = jwtApiClient.requestGetRefreshToken();
        
        jwtTokenRepository.setRefreshToken(refreshToken);
        System.out.println(jwtTokenRepository.getRefreshToken());
        
        jwtTokenRepository.setAccessToken(jwtApiClient.requestGetAccessToken());
        System.out.println(jwtTokenRepository.getAccessToken());
    }

}
