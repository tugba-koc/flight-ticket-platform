package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetSearchFlights;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.FlightRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.Flight;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightManager implements FlightService {

        private JwtService jwtService;

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

/*         private String flightNumber;
        private String departureCity;
        private String arrivalCity;
        private Double price; */

        @Override
        public List<GetSearchFlights> searchFlights(String requestId, String departureCity, String arrivalCity) {
                 List<Flight> flightList = flightRepository.findByDepartureCityAndArrivalCity(departureCity, arrivalCity);
                 List<GetSearchFlights> getSearchFlights = flightList.stream().map(flight ->  
                        GetSearchFlights.builder()
                                .departureCity(flight.getDepartureCity())
                                .arrivalCity(flight.getArrivalCity())
                                .flightNumber(flight.getFlightNumber())
                                .company(flight.getCompany())
                                .departureTime(flight.getDepartureTime())
                                .price(flight.getPrice()).build())
                                        .toList();
                return getSearchFlights;
        }
        
}
