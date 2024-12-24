package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateUserResponse {
        @NotNull(message = "requestId cannot be null")
        @NotEmpty(message = "requestId cannot be empty")
        String requestId;

        @NotNull(message = "ipAddress cannot be null")
        @NotEmpty(message = "ipAddress cannot be empty")
        String ipAddress;
}
