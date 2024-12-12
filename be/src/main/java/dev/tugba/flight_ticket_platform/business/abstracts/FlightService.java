package dev.tugba.flight_ticket_platform.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlightResponse;

public interface FlightService {
        GetAllFlightResponse getAllFlight(String requestId); 
        GetFilterFlightResponse searchFlights(String requestId, String departureCity, String arrivalCity, String departureDay);
}
