package dev.tugba.flight_ticket_platform.webApi.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.UserService;
import dev.tugba.flight_ticket_platform.business.requests.CreateDepositRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateUserResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetDepositResponse;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/deposit")
    @PreAuthorize("hasAuthority('visitor:create')")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<GetDepositResponse> deposit(@RequestHeader("Authorization") String bearerToken, @RequestBody CreateDepositRequest createDepositRequest) {
        return ResponseEntity.ok(userService.deposit(bearerToken, createDepositRequest));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('visitor:create')")
    @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public ResponseEntity<Optional<User>> getUserResponse(@RequestHeader("Authorization") String bearerToken, @RequestBody CreateUserResponse createUserResponse) {
        return ResponseEntity.ok(userService.getUserResponse(bearerToken, createUserResponse));
    }
}
