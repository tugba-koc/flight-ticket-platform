package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.business.abstracts.ValidatorService;
import dev.tugba.flight_ticket_platform.business.responses.GetValidateEmailResponse;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.ValidationException;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ValidatorManager implements ValidatorService {
        private UserRepository userRepository;

        @Override
        public GetValidateEmailResponse validateEmail(String requestId, String email) {
                // if email does not exist in the database, return 200 status code
                if(!userRepository.existsByEmail(email)) {
                                return GetValidateEmailResponse.builder()
                                                .status(200)
                                                .datetime(LocalDateTime.now())
                                                .requestId(requestId)
                                                .build();
                } else {
                                throw new ValidationException("Email already exists");
                }
                
        }

        
}
