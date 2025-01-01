package dev.tugba.flight_ticket_platform.auth.config.components;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.auth.config.abstracts.UserService;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.AuthorizationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, 
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain)
            throws ServletException, IOException {
                final String authHeader = request.getHeader("Authorization");
                final String jwt;
                final String username;

                if(authHeader == null || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
                    filterChain.doFilter(request, response);
                    return;
                }

                jwt = authHeader.substring(7);

                username = jwtService.extractUsername(jwt);
                // it is safe to have a static util method to get the currently logged in user from SecurityContextHolder
                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);
                    if(jwtService.isTokenValid(jwt, userDetails)){
                        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
                        (userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        securityContext.setAuthentication(authToken);
                        SecurityContextHolder.setContext(securityContext);
                    } else {
                        throw new AuthorizationException("Token expired");
                    }
                }
                filterChain.doFilter(request, response);
    }
}
