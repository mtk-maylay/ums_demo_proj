package may.com.mm.component.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import may.com.mm.component.http.error.ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationFailureFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureFilter.class);

    private final ObjectMapper objectMapper;

    public AuthenticationFailureFilter(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        try {

            filterChain.doFilter(request, response);
        } catch (Exception exception) {

            LOGGER.error("Error : ", exception);

            ErrorResponse errorResponse = null;

            if (exception instanceof XAuthHeaderAuthenticator.AuthenticationFailureException authenticationFailureException) {

                errorResponse = new ErrorResponse("AuthenticationFailure", authenticationFailureException.getMessage());

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

            } else {

                errorResponse = new ErrorResponse("RequestError", exception.getMessage());

                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

            }

            response.getWriter()
                    .write(objectMapper.writeValueAsString(errorResponse));
        }

    }

}
