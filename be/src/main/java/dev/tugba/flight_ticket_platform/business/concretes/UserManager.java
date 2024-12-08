package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.business.abstracts.UserService;
import dev.tugba.flight_ticket_platform.business.requests.CreateDepositRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetDepositResponse;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.UserNotFoundException;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
        private final UserRepository userRepository;

        private JwtService jwtService;

        @Override
        public Optional<User> getUserResponse(String bearerToken, CreateUserResponse createUserResponse) {
                String userEmail = jwtService.extractUsername(bearerToken);
                return userRepository.findByEmail(userEmail);
        }

        @Override
        public GetDepositResponse deposit(String bearerToken, CreateDepositRequest createDepositRequest) {
                String userEmail = jwtService.extractUsername(bearerToken);
                User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));
                user.setBalance(createDepositRequest.getAmount());
                userRepository.save(user);
                return GetDepositResponse.builder()
                        .datetime(LocalDate.now())
                        .newAmount(createDepositRequest.getAmount())
                        .build();
        }
}
