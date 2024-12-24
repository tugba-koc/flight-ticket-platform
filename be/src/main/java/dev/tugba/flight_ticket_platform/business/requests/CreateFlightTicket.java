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
        @NotNull(message = "requestId cannot be null")
        @NotEmpty(message = "requestId cannot be empty")
        private String requestId;

        @NotNull(message = "flightNumber cannot be null")
        @NotEmpty(message = "flightNumber cannot be empty")
        private String flightNumber;

        @NotNull(message = "departureCity cannot be null")
        @NotEmpty(message = "departureCity cannot be empty")
        private String departureCity;

        @NotNull(message = "arrivalCity cannot be null")
        @NotEmpty(message = "arrivalCity cannot be empty")
        private String arrivalCity;

        @NotNull(message = "company cannot be null")
        @NotEmpty(message = "company cannot be empty")
        private String company;

        @NotNull(message = "departureDay cannot be null")
        @NotEmpty(message = "departureDay cannot be empty")
        private String departureDay;

        @NotNull(message = "departureHour cannot be null")
        @NotEmpty(message = "departureHour cannot be empty")
        private String departureHour;

        @NotNull(message = "price cannot be null")
        @Positive(message = "price must be positive")
        private Double price;
}
