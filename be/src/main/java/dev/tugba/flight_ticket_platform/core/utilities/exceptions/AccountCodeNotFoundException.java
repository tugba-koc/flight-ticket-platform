package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

public class AccountCodeNotFoundException extends RuntimeException {
    public AccountCodeNotFoundException(String message) {
        super(message);
    }
}
