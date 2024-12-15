package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse {
        private String token;
        private int status;
        private LocalDateTime datetime;
        private String role;
}
