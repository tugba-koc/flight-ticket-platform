package dev.tugba.flight_ticket_platform.business.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetFlightTicketResponse {
        private String message;
}
