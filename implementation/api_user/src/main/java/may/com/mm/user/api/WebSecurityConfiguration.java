package may.com.mm.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import may.com.mm.component.http.MDCFilter;
import may.com.mm.component.spring.security.AuthenticationFailureFilter;
import may.com.mm.component.spring.security.UnauthorizedAccessHandler;
import may.com.mm.component.spring.security.XAuthHeaderAuthenticationFilter;
import may.com.mm.component.spring.security.XAuthHeaderAuthenticator;
import may.com.mm.component.spring.security.XAuthHeaderMDCFilter;
import may.com.mm.user.api.security.UserAPIAuthenticator;
import may.com.mm.user.usecase.domain.FindUserCredentials;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public FilterRegistrationBean<MDCFilter> filterRegistrationBean() {

        FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MDCFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(SecurityProperties.DEFAULT_FILTER_ORDER - 1);

        return registrationBean;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   ObjectMapper objectMapper,
                                                   XAuthHeaderAuthenticator xAuthHeaderAuthenticator) throws Exception {

        http.cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(new UnauthorizedAccessHandler(objectMapper)))
            .authorizeHttpRequests(configure -> configure.requestMatchers("/", "/heart_beat", "/hello_world", "/public/**")
                                                         .permitAll()
                                                         .requestMatchers("/secured/**")
                                                         .authenticated())
            .addFilterBefore(new XAuthHeaderAuthenticationFilter(xAuthHeaderAuthenticator, "Authorization", "X-Access-Key"),
                             UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(new XAuthHeaderMDCFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new AuthenticationFailureFilter(objectMapper), XAuthHeaderAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public XAuthHeaderAuthenticator xAuthHeaderAuthenticator(FindUserCredentials findUserCredentials) {

        return new UserAPIAuthenticator(findUserCredentials);
    }

}
