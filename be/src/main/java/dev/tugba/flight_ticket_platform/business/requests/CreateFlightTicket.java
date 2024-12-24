package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateFlightTicket {
        @NotNull
        @NotEmpty
        private String flightNumber;
        private String departureCity;
        private String arrivalCity;
        private String company;
        private String departureDay;
        private String departureHour;
        private String requestId;


        @NotNull
        @Positive
        private Double price;
}
