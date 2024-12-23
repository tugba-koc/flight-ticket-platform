package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

public class SaveToDBException extends RuntimeException {
    public SaveToDBException(String message) {
        super(message);
    }
}
