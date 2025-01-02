package dev.tugba.flight_ticket_platform.business.abstracts;

import dev.tugba.flight_ticket_platform.business.responses.GetValidateEmailResponse;

public interface ValidatorService {
        GetValidateEmailResponse validateEmail(String requestId, String email);
}
