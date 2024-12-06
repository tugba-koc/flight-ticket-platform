package dev.tugba.flight_ticket_platform.business.concretes;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.auth.config.constants.Role;
import dev.tugba.flight_ticket_platform.business.abstracts.AuthenticateService;
import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
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
        public String createUser(CreateRegisterRequest createRegisterRequest) {
                userRepository.save(User.builder()
                        .id(UUID.randomUUID().toString())
                        .email(createRegisterRequest.getEmail())
                        .password(passwordEncoder.encode(createRegisterRequest.getPassword()))
                        .turkishId(createRegisterRequest.getTurkishId())
                        .name(createRegisterRequest.getName())
                        .surname(createRegisterRequest.getSurname())
                        .phoneNumber(createRegisterRequest.getPhoneNumber())
                        .role(createRegisterRequest.getRole().toString() == "ADMIN" ? Role.ADMIN: Role.VISITOR)
                        .gender(createRegisterRequest.getGender())
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
                        try {
                                // Try to authenticate the user
                                this.authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        loginRequest.getEmail(), loginRequest.getPassword()));
                        } catch (Exception e) {
                                e.printStackTrace();
                                throw new RuntimeException("Accountcode and password are not matching.");
                        }
                        String token = jwtService.generateToken(user);

                        System.out.println("Token: " + token);

                        return LoginResponse.builder()
                                .status(200)
                                .token(token)
                                .datetime(java.time.LocalDateTime.now())
                                .build();
                } else {
                        // TODO: status code should be taken from enum
                        return LoginResponse.builder()
                                .token(null)
                                .status(422)
                                .datetime(java.time.LocalDateTime.now())
                                .build();
                }
        }

        
}
