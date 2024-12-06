package dev.tugba.flight_ticket_platform.business.abstracts;

import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
import dev.tugba.flight_ticket_platform.business.requests.LoginRequest;
import dev.tugba.flight_ticket_platform.business.responses.LoginResponse;

public interface AuthenticateService {
        String createUser(CreateRegisterRequest createUser);
        LoginResponse login(LoginRequest loginRequest);
}
