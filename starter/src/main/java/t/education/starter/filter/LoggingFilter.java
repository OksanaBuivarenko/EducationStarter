package t.education.starter.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long duration = System.currentTimeMillis() - startTime;

        String logMessage = String.format("Request method: %s, request URI: %s, response status: %d, request processing " +
                        "time: %d ms", request.getMethod(), request.getRequestURI(),  response.getStatus(), duration);

        StringBuilder requestHeaderLog = new StringBuilder("Request headers:");
        Collections.list(request.getHeaderNames()).stream().map(header ->
             requestHeaderLog.append(" " + header + " - " + request.getHeader(header) + " ;")).
                collect(Collectors.toList());

        StringBuilder responseHeaderLog = new StringBuilder("Response headers:");
        response.getHeaderNames().stream().map(header ->
                responseHeaderLog.append(" " + header + " - " + response.getHeader(header) + " ;")).
                collect(Collectors.toList());

        logger.info(logMessage);
        logger.info(requestHeaderLog.toString());
        logger.info(requestHeaderLog.toString());
    }
}
