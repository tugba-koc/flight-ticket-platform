package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SellFlightRequest {
        @NotEmpty(message = "requestId cannot be empty")
        @NotNull(message = "requestId cannot be null")
        private String requestId;

        @NotEmpty(message = "flightId cannot be empty")
        @NotNull(message = "flightId cannot be null")
        private String flightId;
}
