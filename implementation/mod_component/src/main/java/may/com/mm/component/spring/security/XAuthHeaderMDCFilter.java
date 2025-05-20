package may.com.mm.component.spring.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class XAuthHeaderMDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null) {

                var details = (String) authentication.getPrincipal();

                MDC.put("USER_ID", details);
            }

            filterChain.doFilter(request, response);

        }finally {

            MDC.clear();
        }
    }

}
