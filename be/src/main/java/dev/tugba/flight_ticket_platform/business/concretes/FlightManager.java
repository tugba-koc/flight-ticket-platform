package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.FlightRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.Flight;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightManager implements FlightService {

        private final FlightRepository flightRepository;

        @Override
        public GetAllFlightResponse  getAllFlight() {
                List<Flight> flightList =  flightRepository.findAll();
                GetAllFlightResponse getAllFlightResponse = GetAllFlightResponse.builder()
                                .datetime(LocalDateTime.now())
                                .flightDataList(flightList)
                                .status(200)
                                .requestId(UUID.randomUUID().toString())
                                .build();

                        return getAllFlightResponse;

        }
        
}
