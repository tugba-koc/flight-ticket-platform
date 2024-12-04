package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import dev.tugba.flight_ticket_platform.entities.concretes.Flight;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUserResponse {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private int status;
    private LocalDateTime datetime;
    private String requestId;
}
