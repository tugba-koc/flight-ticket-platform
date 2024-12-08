package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetDepositResponse {
        private int newAmount;
        private LocalDate datetime;
}
