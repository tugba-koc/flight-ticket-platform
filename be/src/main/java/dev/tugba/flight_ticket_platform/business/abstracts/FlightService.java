package dev.tugba.flight_ticket_platform.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import dev.tugba.flight_ticket_platform.business.requests.CreateFlightTicket;
import dev.tugba.flight_ticket_platform.business.requests.SellFlightRequest;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFlightTicketResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetSellFlightResponse;

public interface FlightService {
        GetAllFlightResponse getAllFlight(String requestId); 
        GetFilterFlightResponse searchFlights(String requestId, String departureCity, String arrivalCity, String departureDay);
        GetSellFlightResponse sellFlight(String token, SellFlightRequest sellFlightRequest);
        GetFlightTicketResponse addFlight(String token, CreateFlightTicket createFlightTicket);
}
