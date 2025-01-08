package dev.tugba.flight_ticket_platform.business.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateForgotPasswordRequest {
        @NotNull(message = "birthDate cannot be null")
        private LocalDate birthDate;

        @NotNull(message = "email cannot be null")
        @NotEmpty(message = "email cannot be empty")
        private String email;

        @NotNull(message = "phoneNumber cannot be null")
        @NotEmpty(message = "phoneNumber cannot be empty")
        private String phoneNumber;

        @NotNull(message = "requestId cannot be null")
        @NotEmpty(message = "requestId cannot be empty")
        private String requestId;
}
