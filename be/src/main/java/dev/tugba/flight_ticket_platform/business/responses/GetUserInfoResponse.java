package dev.tugba.flight_ticket_platform.business.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetUserInfoResponse {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private int balance;
    private LocalDateTime datetime;
}