package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateDepositRequest {
        @NotNull
        @NotEmpty
        private String requestId;

        @NotNull
        @Positive
        private Double amount;
}
