package dev.tugba.flight_ticket_platform.business.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetSearchFlights {
        private String flightNumber;
        private String departureCity;
        private String arrivalCity;
        private Double price;
        private String company;
        private String departureTime;
}
