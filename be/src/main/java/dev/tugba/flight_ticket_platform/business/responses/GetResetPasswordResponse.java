package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetResetPasswordResponse {
        private int status;
        private String requestId;
        private LocalDateTime datetime;
}
