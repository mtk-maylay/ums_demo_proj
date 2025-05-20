package may.com.mm.component.http;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import may.com.mm.component.utility.Snowflake;
import org.slf4j.MDC;

import java.io.IOException;

public class MDCFilter implements Filter {

    @Override
    public void destroy() {
        // Cleanup logic, if needed
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        try {

            MDC.put("REQ_ID", String.valueOf(Snowflake.get().nextId()));
            filterChain.doFilter(servletRequest, servletResponse);

        } finally {
            MDC.clear();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if needed
    }

}