package dev.tugba.flight_ticket_platform.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.abstracts.UserService;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserRequest;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetUserResponse;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('visitor:read')")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<Optional<User>> getUserResponse(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(this.userService.getUserResponse(bearerToken));
    }

    @PostMapping("/register")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(this.userService.createUser(createUserRequest));
    }
}
