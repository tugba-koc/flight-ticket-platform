package dev.tugba.flight_ticket_platform.auth.config;

import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException, java.io.IOException {
                if(request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer ") || request.getHeader("Authorization").split(" ")[1].equals("null")) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // 403 forbidden (token is removed by the user)
                        response.setContentType("application/json");
                        String errorMessage = String.format("{\"error\":\"Unauthorized: Missing JWT token\", \"status\": %d, \"date\": \"%s\", \"message\": \"%s\"}", HttpServletResponse.SC_FORBIDDEN, java.time.LocalDateTime.now(), "Authorization Exception");
                        response.getWriter().write(errorMessage);
                        return;
                }
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 unauthorized
                response.setContentType("application/json");
                String errorMessage = String.format("{\"error\":\"Unauthorized: Invalid JWT token\", \"status\": %d, \"date\": \"%s\", \"message\": \"%s\"}", HttpServletResponse.SC_FORBIDDEN, java.time.LocalDateTime.now(), "Authorization Exception");
                response.getWriter().write(errorMessage);
        }
}
