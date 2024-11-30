package dev.tugba.flight_ticket_platform.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetAllFlightData {
        private int id;
        private String body;
        private String title;
}
