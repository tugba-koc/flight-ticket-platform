package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.business.abstracts.UserService;
import dev.tugba.flight_ticket_platform.business.requests.CreateDepositRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetDepositResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetUserInfoResponse;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.UserNotFoundException;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
        private final UserRepository userRepository;
        private final JwtService jwtService;

        @Override
        public User getUserResponse(String bearerToken, CreateUserResponse createUserResponse) {
                try {
                        String userEmail = jwtService.extractUsername(bearerToken);
                        return userRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));
                } catch (UserNotFoundException e) {
                        throw new UserNotFoundException(e.getMessage());
                }
        }

        @Override
        public GetDepositResponse deposit(String bearerToken, CreateDepositRequest createDepositRequest) {
                String userEmail = jwtService.extractUsername(bearerToken);

                User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not found"));

                user.setBalance(createDepositRequest.getAmount());

                userRepository.save(user);

                return GetDepositResponse.builder()
                        .datetime(LocalDateTime.now())
                        .requestId(createDepositRequest.getRequestId())
                        .status(200)
                        .newAmount(createDepositRequest.getAmount())
                        .build();
        }

        @Override
        public GetUserInfoResponse getUserInfo(String bearerToken, String requestId) {
                String userEmail = jwtService.extractUsername(bearerToken);

                User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not found"));

                return GetUserInfoResponse.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .balance(user.getBalance())
                        .phoneNumber(user.getPhoneNumber())
                        .datetime(LocalDateTime.now())
                        .role(user.getRole().name())
                        .requestId(requestId)
                        .status(200)
                        .build();
        }
}
