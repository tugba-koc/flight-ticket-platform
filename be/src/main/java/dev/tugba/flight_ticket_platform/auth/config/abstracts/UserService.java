package dev.tugba.flight_ticket_platform.auth.config.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {
    public UserDetailsService userDetailsService();
}
