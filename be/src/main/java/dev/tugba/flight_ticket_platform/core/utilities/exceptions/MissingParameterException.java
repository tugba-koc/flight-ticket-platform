package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

public class MissingParameterException extends RuntimeException {
    public MissingParameterException(String message) {
        super(message);
    }
}
