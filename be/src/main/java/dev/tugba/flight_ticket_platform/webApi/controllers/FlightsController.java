package dev.tugba.flight_ticket_platform.webApi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetSearchFlights;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class FlightsController {

    private FlightService flightService;

    @GetMapping("/flights/all")
    public ResponseEntity<GetAllFlightResponse> getAllFlight() {
        return ResponseEntity.ok(flightService.getAllFlight());
    }

    @GetMapping("/api/v1/flights/search")
    public ResponseEntity<List<GetSearchFlights>> searchFlights(@RequestHeader("requestId") String requestId, @RequestHeader("departureCity") String departureCity, @RequestHeader("arrivalCity") String arrivalCity) {
        return ResponseEntity.ok(flightService.searchFlights(requestId, departureCity, arrivalCity));
    }
}
