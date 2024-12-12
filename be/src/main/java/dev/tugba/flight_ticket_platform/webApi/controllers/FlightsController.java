package dev.tugba.flight_ticket_platform.webApi.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlightResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class FlightsController {

    private FlightService flightService;

    @GetMapping("/flights/all")
    public ResponseEntity<GetAllFlightResponse> getAllFlight(@RequestParam("requestId") String requestId) {
        return ResponseEntity.ok(flightService.getAllFlight(requestId));
    }

    @GetMapping("/flights/search")
    public ResponseEntity<GetFilterFlightResponse> searchFlights(@RequestParam("requestId") String requestId, @RequestParam("departureCity") String departureCity, @RequestParam("arrivalCity") String arrivalCity, @RequestParam("departureDay") String departureDay ) {
        System.out.println("requestId: " + requestId);
        return ResponseEntity.ok(flightService.searchFlights(requestId, departureCity, arrivalCity, departureDay));
    }
}
