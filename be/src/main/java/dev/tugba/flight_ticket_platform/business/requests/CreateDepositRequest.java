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
        @NotNull(message = "requestId cannot be null")
        @NotEmpty(message = "requestId cannot be empty")
        private String requestId;

        @NotNull(message = "amount cannot be null")
        @Positive(message = "amount must be positive")
        private Double amount;
}
