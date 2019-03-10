package akademia.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private final static String SECURED_PATTERN = "/api/**";
    private final static String RESOURCE_ID = "resource-service-rest-api";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources.resourceId(RESOURCE_ID));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http
               .antMatcher("/**")
               .authorizeRequests()
               .anyRequest()
               .authenticated();
    }
}
