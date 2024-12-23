package dev.tugba.flight_ticket_platform.webApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.requests.CreateFlightTicket;
import dev.tugba.flight_ticket_platform.business.requests.SellFlightRequest;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFlightTicketResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetSellFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetUserFlightResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
        return ResponseEntity.ok(flightService.searchFlights(requestId, departureCity, arrivalCity, departureDay));
    }

    @PostMapping("/flight/sell")
    @PreAuthorize("hasAuthority('visitor:update')")
    public ResponseEntity<GetSellFlightResponse> sellFlight(@RequestHeader("Authorization") String token,  @RequestBody @Valid SellFlightRequest sellFlightRequest) {
        return ResponseEntity.ok(flightService.sellFlight(token, sellFlightRequest));
    }

    @PostMapping("/flight/add")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<GetFlightTicketResponse> addFlight(@RequestHeader("Authorization") String token,  @RequestBody @Valid CreateFlightTicket createFlightTicket) {
        return ResponseEntity.ok(flightService.addFlight(token, createFlightTicket));
    }

    @GetMapping("/flights/list")
    @PreAuthorize("hasAnyAuthority('visitor:read', 'admin:read')")
    public ResponseEntity<GetUserFlightResponse> listUserFlights(@RequestHeader("Authorization") String token, @RequestParam("requestId") String requestId) {
        return ResponseEntity.ok(flightService.listUserFlights(token, requestId));
    }
}
