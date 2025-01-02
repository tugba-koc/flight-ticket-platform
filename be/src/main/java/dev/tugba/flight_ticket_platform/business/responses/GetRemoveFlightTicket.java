package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetRemoveFlightTicket {
        private int status;
        private LocalDateTime datetime;
        private String requestId;
}
