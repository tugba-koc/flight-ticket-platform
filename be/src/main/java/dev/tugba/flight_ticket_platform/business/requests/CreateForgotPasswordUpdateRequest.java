package dev.tugba.flight_ticket_platform.business.requests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateForgotPasswordUpdateRequest {
        private String requestId;
        private String newPassword;
        private String confirmPassword;
        private String email;
        private String phoneNumber;
        private LocalDate birthDate;
}
