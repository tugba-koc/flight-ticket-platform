package dev.tugba.flight_ticket_platform.auth.config.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.UserService;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.UserNotFoundException;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.helper.Helper;
import lombok.AllArgsConstructor;

@Service("authUserManager")  // Bean adı burada da benzersiz
@AllArgsConstructor
public class AuthUserManager implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        // Instead of a lambda, we are directly using the UserDetailsService implementation
        return new UserDetailsServiceImpl(userRepository);
    }

    // Custom class implementing UserDetailsService
    public static class UserDetailsServiceImpl implements UserDetailsService {
        private final UserRepository userRepository;

        public UserDetailsServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) {
            if (Helper.isValidEmail(username)) {
                return userRepository.findByEmail(username)
                        .map(user -> (UserDetails) user)
                        .orElseThrow(() -> new UserNotFoundException("No user found with this email"));
            } else {
                return userRepository.findByTurkishId(username)
                        .map(user -> (UserDetails) user)
                        .orElseThrow(() -> new UserNotFoundException("No user found with this Turkish ID"));
            }
        }
    }
}
