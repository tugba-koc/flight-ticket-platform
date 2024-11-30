package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}