package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlight;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlightResponse;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.FlightRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.Flight;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightManager implements FlightService {
        private final FlightRepository flightRepository;

        @Override
        public GetAllFlightResponse getAllFlight(String requestId) {
                List<Flight> flightList = flightRepository.findAll();
                GetAllFlightResponse getAllFlightResponse = GetAllFlightResponse.builder()
                        .datetime(LocalDateTime.now())
                        .flightDataList(flightList)
                        .status(200)
                        .build();

                        return getAllFlightResponse;

        }

        @Override
        public GetFilterFlightResponse searchFlights(String requestId, String departureCity, String arrivalCity, String departureDay) {
                 List<Flight> flightList = flightRepository.findByDepartureCityAndArrivalCityAndDepartureDay(departureCity, arrivalCity, departureDay);
                 List<GetFilterFlight> filterFlightDataList = flightList.stream().map(flight ->  
                        GetFilterFlight.builder()
                                .departureCity(flight.getDepartureCity())
                                .arrivalCity(flight.getArrivalCity())
                                .flightNumber(flight.getFlightNumber())
                                .company(flight.getCompany())
                                .departureDay(flight.getDepartureDay())
                                .departureHour(flight.getDepartureHour())
                                .price(flight.getPrice()).build())
                                        .toList();
                return GetFilterFlightResponse.builder()
                        .datetime(LocalDateTime.now())
                        .filterFlightDataList(filterFlightDataList)
                        .status(200)
                        .build();
        }
}
