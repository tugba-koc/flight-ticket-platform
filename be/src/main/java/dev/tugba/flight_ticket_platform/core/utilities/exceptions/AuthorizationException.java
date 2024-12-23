package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
