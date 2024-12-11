package dev.tugba.flight_ticket_platform.webApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/flights")
@AllArgsConstructor
public class FlightsController {

    private FlightService flightService;

    @GetMapping
    public ResponseEntity<GetAllFlightResponse> getAllFlight() {
        return ResponseEntity.ok(flightService.getAllFlight());
    }
}
