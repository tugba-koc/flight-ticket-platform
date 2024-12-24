package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@NotNull
@NotEmpty
public class SellFlightRequest {
        private String requestId;
        private String flightId;
}
