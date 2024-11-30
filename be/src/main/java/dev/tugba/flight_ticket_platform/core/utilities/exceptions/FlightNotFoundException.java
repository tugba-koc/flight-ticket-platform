package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}