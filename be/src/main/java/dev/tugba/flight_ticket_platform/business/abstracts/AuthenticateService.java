package dev.tugba.flight_ticket_platform.business.abstracts;

import dev.tugba.flight_ticket_platform.business.requests.CreateUserRequest;
import dev.tugba.flight_ticket_platform.business.requests.LoginRequest;
import dev.tugba.flight_ticket_platform.business.responses.LoginResponse;

public interface AuthenticateService {
        String createUser(CreateUserRequest createUser);
        LoginResponse login(LoginRequest loginRequest);
}
