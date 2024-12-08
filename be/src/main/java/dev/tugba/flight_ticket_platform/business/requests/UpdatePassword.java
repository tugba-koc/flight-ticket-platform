package dev.tugba.flight_ticket_platform.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UpdatePassword {
        private String password;
        private String newPassword;
        private String requestId;
}
