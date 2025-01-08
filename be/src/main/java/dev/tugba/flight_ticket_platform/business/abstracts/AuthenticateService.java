package dev.tugba.flight_ticket_platform.business.abstracts;

import dev.tugba.flight_ticket_platform.business.requests.CreateForgotPasswordRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
import dev.tugba.flight_ticket_platform.business.requests.LoginRequest;
import dev.tugba.flight_ticket_platform.business.requests.UpdatePassword;
import dev.tugba.flight_ticket_platform.business.responses.GetForgotPasswordCheckResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetResetPasswordResponse;
import dev.tugba.flight_ticket_platform.business.responses.LoginResponse;

public interface AuthenticateService {
        String createUser(CreateRegisterRequest createUser);
        LoginResponse login(LoginRequest loginRequest);
        GetResetPasswordResponse resetPassword(String bearerToken, UpdatePassword updatePassword);
        GetForgotPasswordCheckResponse forgotPasswordCheck(CreateForgotPasswordRequest createForgotPasswordRequest);
}
