package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationServiceException extends AuthenticationException {
    public AuthenticationServiceException(String message) {
        super(message);
    }
}