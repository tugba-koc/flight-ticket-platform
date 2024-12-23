package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUserFlightResponse {
        private List<GetFilterFlight> flights;
        private int status;
        private LocalDateTime datetime;
        private String requestId;
}
