package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.requests.SellFlightRequest;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlight;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetSellFlightResponse;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.FlightNotFoundException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.UserNotFoundException;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.FlightRepository;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.Flight;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FlightManager implements FlightService {
        private final FlightRepository flightRepository;
        private final UserRepository userRepository;
        private JwtService jwtService;

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
                                .id(flight.getId())
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

/*         private LocalDateTime datetime;
        private String status;
        private String requestId; */
        @Override
        public GetSellFlightResponse sellFlight(String token, SellFlightRequest sellFlightRequest) {
                try {
                        String userEmail = jwtService.extractUsername(token);

                        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));
                        
                        Flight flight = flightRepository.findById(sellFlightRequest.getFlightId()).orElseThrow(() -> new FlightNotFoundException("Flight not found"));

                        Double flightPrice = flight.getPrice();
                        Double userBalance = user.getBalance();
                        System.out.println("balances");
                        System.out.println(userBalance);
                        System.out.println(flightPrice);

                        if(userBalance < flightPrice) {
                                throw new RuntimeException("Insufficient balance");
                        } else {
                                user.setBalance(userBalance - flightPrice);
                        }

                        List<String> flightTicketIds = user.getFlightTicketIds();
                        if (flightTicketIds == null) {
                            flightTicketIds = new ArrayList<>();
                        }
                        flightTicketIds.add(sellFlightRequest.getFlightId());
                        user.setFlightTicketIds(flightTicketIds);

                        userRepository.save(user);

                        return GetSellFlightResponse.builder()
                                .datetime(LocalDateTime.now())
                                .status(200)
                                .requestId(UUID.randomUUID().toString())
                                .build();
                        }

                // TODO: it is a good structure to catch exceptions separately
                catch (UserNotFoundException e) {
                        throw new UserNotFoundException("\"User not found");
                } catch (FlightNotFoundException e) {
                        throw new FlightNotFoundException("Flight not found");
                } catch (RuntimeException e) {
                        throw new RuntimeException("Insufficient balance");
                } catch (Exception e) {
                        throw new RuntimeException("An error occurred while selling the flight");
                }
        }
}
