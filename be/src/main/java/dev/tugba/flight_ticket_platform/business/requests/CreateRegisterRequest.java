package dev.tugba.flight_ticket_platform.business.requests;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

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
        private String address;
        private String birthDate;
        private String role;   
        private String gender;
}
