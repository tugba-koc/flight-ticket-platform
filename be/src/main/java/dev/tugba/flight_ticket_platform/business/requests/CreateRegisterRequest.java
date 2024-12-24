package dev.tugba.flight_ticket_platform.business.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@NotNull
@NotEmpty
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
