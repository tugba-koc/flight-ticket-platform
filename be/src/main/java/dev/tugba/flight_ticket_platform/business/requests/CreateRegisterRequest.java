package dev.tugba.flight_ticket_platform.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateRegisterRequest {
        private String turkishId;
        private String name;
        private String surname;
        private String email;
        private String password;
        private String phoneNumber;
        private String birthDate;
        private String gender;
}
