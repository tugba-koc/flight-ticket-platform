package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdatePassword {
        @NotNull(message = "password cannot be null")
        @NotEmpty(message = "password cannot be empty")
        private String password;

        @NotNull(message = "newPassword cannot be null")
        @NotEmpty(message = "newPassword cannot be empty")
        private String newPassword;

        @NotNull(message = "requestId cannot be null")
        @NotEmpty(message = "requestId cannot be empty")
        private String requestId;
}
