package dev.tugba.flight_ticket_platform.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateFlightTicket {
        private String flightNumber;
        private String departureCity;
        private String arrivalCity;
        private String company;
        private String departureDay;
        private String departureHour;
        private Double price;
}
