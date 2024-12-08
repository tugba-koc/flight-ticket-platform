package dev.tugba.flight_ticket_platform.business.abstracts;


import java.util.Optional;

import dev.tugba.flight_ticket_platform.business.requests.CreateDepositRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetDepositResponse;
import dev.tugba.flight_ticket_platform.entities.concretes.User;

public interface UserService {
        Optional<User> getUserResponse(String bearerToken, CreateUserResponse createUserResponse);
        GetDepositResponse deposit(String bearerToken, CreateDepositRequest createDepositRequest);
}
