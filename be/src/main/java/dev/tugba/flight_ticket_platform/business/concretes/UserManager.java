package dev.tugba.flight_ticket_platform.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.business.abstracts.UserService;
import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserResponse;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
        private final UserRepository userRepository;

        private JwtService jwtService;

        @Override
        public Optional<User> getUserResponse(String token, CreateUserResponse createUserResponse) {
                String tokenJWT = jwtService.extractUsername(token);
                System.out.println("tokenJWT: " + tokenJWT);
                return userRepository.findByEmail(tokenJWT);
        }
}
