package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;
import java.util.List;

import dev.tugba.flight_ticket_platform.entities.concretes.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetAllFlightResponse {
    private List<Flight> flightDataList;
    private int status;
    private LocalDateTime datetime;
    private String requestId;
}
