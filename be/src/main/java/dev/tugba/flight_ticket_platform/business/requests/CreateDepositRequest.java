package dev.tugba.flight_ticket_platform.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateDepositRequest {
        private String requestId;
        private int amount;
}
