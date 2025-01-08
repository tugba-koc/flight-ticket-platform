package dev.tugba.flight_ticket_platform.business.requests;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateRegisterRequest {
        @NotNull(message = "turkishId cannot be null")
        @NotEmpty(message = "turkishId cannot be empty")
        private String turkishId;

        @NotNull(message = "name cannot be null")
        @NotEmpty(message = "name cannot be empty")
        private String name;

        @NotNull(message = "surname cannot be null")
        @NotEmpty(message = "surname cannot be empty")
        private String surname;

        @NotNull(message = "email cannot be null")
        @NotEmpty(message = "email cannot be empty")
        private String email;

        @NotNull(message = "password cannot be null")
        @NotEmpty(message = "password cannot be empty")
        private String password;

        @NotNull(message = "phoneNumber cannot be null")
        @NotEmpty(message = "phoneNumber cannot be empty")
        private String phoneNumber;

        @NotNull(message = "birthDate cannot be null")
        private LocalDate birthDate;

        @NotNull(message = "gender cannot be null")
        @NotEmpty(message = "gender cannot be empty")
        private String gender;
}
