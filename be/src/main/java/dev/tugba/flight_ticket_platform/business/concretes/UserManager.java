package dev.tugba.flight_ticket_platform.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.business.abstracts.UserService;
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
}
