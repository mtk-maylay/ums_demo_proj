package may.com.mm.component.spring.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import may.com.mm.component.http.CachedBodyHttpServletRequest;
import may.com.mm.component.exception.AccessAuthenticationFailedException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class XAuthHeaderAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(XAuthHeaderAuthenticationFilter.class);

    private final XAuthHeaderAuthenticator xauthHeaderAuthenticator;

    private final String authorizationHeaderName;

    private final String accessKeyHeaderName;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        var xAuthHeader = request.getHeader(this.authorizationHeaderName);
        var xAccessKey = request.getHeader(this.accessKeyHeaderName);

        if (xAuthHeader == null || xAuthHeader.isEmpty() || xAccessKey == null || xAccessKey.isEmpty()) {

            // If required headers are not found, just proceed to next filter.
            // The subsequent filters will reject the request due to missing authentication data.
            filterChain.doFilter(request, response);
        } else {

            var cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                try {

                    var details = this.xauthHeaderAuthenticator.authenticate(xAuthHeader, xAccessKey, cachedBodyHttpServletRequest);

                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                    UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(details.principalId(), details.credentials(), authorities);

                    authenticationToken.setDetails(details);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                } catch (AccessAuthenticationFailedException ex) {

                    LOGGER.warn("Authentication failed: {}", ex.getMessage());

                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                    return;
                }
            }

            filterChain.doFilter(cachedBodyHttpServletRequest, response);
        }
    }

}
