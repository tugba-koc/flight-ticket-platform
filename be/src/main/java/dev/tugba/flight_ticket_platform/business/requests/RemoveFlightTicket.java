package dev.tugba.flight_ticket_platform.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RemoveFlightTicket {
        private String requestId;
        private String flightTicketId;
}
