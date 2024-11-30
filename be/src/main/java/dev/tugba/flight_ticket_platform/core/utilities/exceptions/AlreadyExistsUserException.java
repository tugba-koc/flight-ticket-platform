package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

public class AlreadyExistsUserException extends RuntimeException {
    public AlreadyExistsUserException(String message) {
        super(message);
    }
}
