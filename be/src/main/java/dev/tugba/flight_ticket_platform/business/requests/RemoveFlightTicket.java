package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RemoveFlightTicket {
        @NotNull(message = "requestId cannot be null")
        @NotEmpty(message = "requestId cannot be empty")
        private String requestId;

        @NotNull(message = "flightTicketId cannot be null")
        @NotEmpty(message = "flightTicketId cannot be empty")
        private String flightTicketId;
}
