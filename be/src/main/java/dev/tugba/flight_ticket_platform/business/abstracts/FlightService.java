package dev.tugba.flight_ticket_platform.business.abstracts;


import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetSearchFlights;

public interface FlightService {
        GetAllFlightResponse getAllFlight(); 
        List<GetSearchFlights> searchFlights(String requestId, String departureCity, String arrivalCity);
}
