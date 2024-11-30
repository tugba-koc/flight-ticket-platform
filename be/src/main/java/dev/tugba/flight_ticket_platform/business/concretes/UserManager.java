package dev.tugba.flight_ticket_platform.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.business.abstracts.UserService;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserRequest;
import dev.tugba.flight_ticket_platform.business.responses.GetUserResponse;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
        private final UserRepository userRepository;

        @Override
        public Optional<User> getUserResponse(String turkishId) {
                return userRepository.findByTurkishId(turkishId);
        }


        @Override
        public String createUser(CreateUserRequest createUserRequest) {
                userRepository.save(User.builder()
                                .email(createUserRequest.getEmail())
                                .password(createUserRequest.getPassword())
                                .turkishId(createUserRequest.getTurkishId())
                                .name(createUserRequest.getName())
                                .surname(createUserRequest.getSurname())
                                .phoneNumber(createUserRequest.getPhoneNumber())
                                .roles(List.of(createUserRequest.getRole()))
                                .gender(createUserRequest.getGender())
                                .createdAt(java.time.LocalDateTime.now())
                                .updatedAt(java.time.LocalDateTime.now())
                                .build());
                String ok = "ok";
                return ok;
        }
}
