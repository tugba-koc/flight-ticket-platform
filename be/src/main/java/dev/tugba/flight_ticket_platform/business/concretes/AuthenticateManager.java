package dev.tugba.flight_ticket_platform.business.concretes;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.auth.config.constants.Role;
import dev.tugba.flight_ticket_platform.business.abstracts.AuthenticateService;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserRequest;
import dev.tugba.flight_ticket_platform.business.requests.LoginRequest;
import dev.tugba.flight_ticket_platform.business.responses.LoginResponse;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticateManager implements AuthenticateService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        @Override
        public String createUser(CreateUserRequest createUserRequest) {
                userRepository.save(User.builder()
                                .email(createUserRequest.getEmail())
                                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                                .turkishId(createUserRequest.getTurkishId())
                                .name(createUserRequest.getName())
                                .surname(createUserRequest.getSurname())
                                .phoneNumber(createUserRequest.getPhoneNumber())
                                .role(createUserRequest.getRole().toString() == "ADMIN" ? Role.ADMIN: Role.VISITOR)
                                .gender(createUserRequest.getGender())
                                .createdAt(java.time.LocalDateTime.now())
                                .updatedAt(java.time.LocalDateTime.now())
                                .build());
                String ok = "ok";
                return ok;
        }

        @Override
        public LoginResponse login(LoginRequest loginRequest) {
                User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
                
                if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                        System.out.println("User found");
                        try {
                                // Try to authenticate the user
                                this.authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        loginRequest.getEmail(), loginRequest.getPassword()));
                        } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException("Accountcode and password are not matching.");
                        }
                        String token = this.jwtService.generateToken(user);

                        return LoginResponse.builder()
                                .status(200)
                                .token(token)
                                .datetime(java.time.LocalDateTime.now())
                                .build();
                } else {
                        System.out.println("User not found");
                        return LoginResponse.builder()
                                        .token(null)
                                        .status(422)
                                        .datetime(java.time.LocalDateTime.now())
                                        .build();
                }
        }

        
}
