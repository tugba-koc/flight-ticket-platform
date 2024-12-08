package dev.tugba.flight_ticket_platform.webApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.AuthenticateService;
import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
import dev.tugba.flight_ticket_platform.business.requests.LoginRequest;
import dev.tugba.flight_ticket_platform.business.requests.UpdatePassword;
import dev.tugba.flight_ticket_platform.business.responses.LoginResponse;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.AuthenticationServiceException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticateService authenticateService;
        
    @PostMapping("/register")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateRegisterRequest createRegisterRequest) {
        return ResponseEntity.ok(authenticateService.createUser(createRegisterRequest));
    }

    @PostMapping("/login")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(this.authenticateService.login(loginRequest));
    }

    @PostMapping("/reset-password")
    @PreAuthorize("hasAuthority('visitor:create')")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<String> resetPassword(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid UpdatePassword updatePassword) {
        return ResponseEntity.ok(authenticateService.resetPassword(bearerToken, updatePassword));
    }
}
