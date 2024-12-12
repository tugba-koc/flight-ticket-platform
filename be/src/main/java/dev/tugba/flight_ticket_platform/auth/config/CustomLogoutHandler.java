package dev.tugba.flight_ticket_platform.auth.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import dev.tugba.flight_ticket_platform.dataAccess.abstracts.TokenRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {
    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);
        Token storedToken = tokenRepository.findByToken(token).orElse(null);

        if (storedToken != null) {
            storedToken.setValid(false);
            tokenRepository.save(storedToken);

            try {
                // Set response content type and write JSON response
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"message\": \"Logout successful\"}");
                response.setStatus(HttpServletResponse.SC_OK); // HTTP 200
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

