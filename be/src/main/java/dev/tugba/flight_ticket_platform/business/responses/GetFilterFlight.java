package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetFilterFlight {
        private String id;
        private String departureCity;
        private String arrivalCity;
        private Double price;
        private String company;
        private String departureDay;
        private String departureHour;
}
