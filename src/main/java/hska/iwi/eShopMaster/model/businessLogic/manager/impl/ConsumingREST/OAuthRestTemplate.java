package hska.iwi.eShopMaster.model.businessLogic.manager.impl.ConsumingREST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class OAuthRestTemplate {

    /**
     * Template to use to make api calls. You have to login for it to be available.
     */
    public static OAuth2RestTemplate temp;
    
    protected static OAuth2ProtectedResourceDetails oAuth2ResourceDetails(String username, String password) {
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();

        details.setClientId("frontendId");
        details.setClientSecret("frontendSecret");
        details.setAccessTokenUri("http://zuul:8092/oauth/token");
        //details.setGrantType("client_credentials");
        List<String> scope = new ArrayList<>();
        scope.add("read");scope.add("write");
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("Frontend");
        details.setUsername(username);
        details.setPassword(password);

        //log.info("OAUTH DETAILS" + clientId + clientSecret + details.getAccessTokenUri() + details.getGrantType());
        //details.set
        return details;
    }

    public static boolean login(String username, String password) {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        temp = new OAuth2RestTemplate(oAuth2ResourceDetails(username, password), new DefaultOAuth2ClientContext(atr));
        OAuth2AccessToken token = temp.getAccessToken();
        if (token != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void logout() {
        temp = null;
    }

}