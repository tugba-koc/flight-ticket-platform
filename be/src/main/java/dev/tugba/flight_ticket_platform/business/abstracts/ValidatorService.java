package dev.tugba.flight_ticket_platform.business.abstracts;

import dev.tugba.flight_ticket_platform.business.responses.GetValidateResponse;

public interface ValidatorService {
        GetValidateResponse validateEmail(String requestId, String email);
        GetValidateResponse validateTurkishId(String requestId, String tcId);
}
