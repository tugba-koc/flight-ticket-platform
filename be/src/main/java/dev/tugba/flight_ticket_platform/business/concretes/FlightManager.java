package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.business.abstracts.FlightService;
import dev.tugba.flight_ticket_platform.business.requests.CreateFlightTicket;
import dev.tugba.flight_ticket_platform.business.requests.SellFlightRequest;
import dev.tugba.flight_ticket_platform.business.responses.GetAllFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlight;
import dev.tugba.flight_ticket_platform.business.responses.GetFilterFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetFlightTicketResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetSellFlightResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetUserFlightResponse;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.AuthorizationException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.FlightNotFoundException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.InvalidCredentialsException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.SaveToDBException;
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
                try {
                        List<Flight> flightList = flightRepository.findAll();

                        if(flightList == null) {
                                throw new FlightNotFoundException("Flight not found");
                        }
                        GetAllFlightResponse getAllFlightResponse = GetAllFlightResponse.builder()
                                .datetime(LocalDateTime.now())
                                .flightDataList(flightList)
                                .requestId(requestId)
                                .status(200)
                                .build();
        
                                return getAllFlightResponse;
                } catch (FlightNotFoundException e) {
                        throw new FlightNotFoundException(e.getMessage());
                }
 
        }

        @Override
        public GetFilterFlightResponse searchFlights(String requestId, String departureCity, String arrivalCity, String departureDay) {
                try {
                        List<Flight> flightList = flightRepository.findByDepartureCityAndArrivalCityAndDepartureDay(departureCity, arrivalCity, departureDay).orElseThrow(()-> new FlightNotFoundException("Flight not found"));
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
                                .requestId(requestId)
                                .datetime(LocalDateTime.now())
                                .filterFlightDataList(filterFlightDataList)
                                .status(200)
                                .build();
                } catch (FlightNotFoundException e) {
                        throw new FlightNotFoundException(e.getMessage());
                }
        }

        @Override
        public GetSellFlightResponse sellFlight(String token, SellFlightRequest sellFlightRequest) {
                try {
                        String userEmail = jwtService.extractUsername(token);

                        User user = userRepository.findByEmail(userEmail).orElseThrow(()-> new UserNotFoundException("User not found"));
                        
                        Flight flight = flightRepository.findById(sellFlightRequest.getFlightId()).orElseThrow(() -> new FlightNotFoundException("Flight not found"));

                        Double flightPrice = flight.getPrice();
                        Double userBalance = user.getBalance();

                        if (userBalance < flightPrice) {
                                throw new RuntimeException("Insufficient balance");
                        } else {
                                user.setBalance(userBalance - flightPrice);
                        }

                        List<String> flightTicketIds = user.getFlightTicketIds();
                        if (flightTicketIds == null) {
                            flightTicketIds = new ArrayList<>();
                        }

                        if (!sellFlightRequest.getFlightId().isEmpty()) {
                                flightTicketIds.add(sellFlightRequest.getFlightId());
                                user.setFlightTicketIds(flightTicketIds);
                        } else {
                                throw new InvalidCredentialsException("FlightId is required");
                        }
                        

                        try {
                                userRepository.save(user);
                        } catch (SaveToDBException e) {
                                throw new SaveToDBException("An error occurred while saving to the database");
                        }

                        return GetSellFlightResponse.builder()
                                .datetime(LocalDateTime.now())
                                .status(200)
                                .requestId(sellFlightRequest.getRequestId())
                                .build();
                } catch (UserNotFoundException e) {
                        throw new UserNotFoundException(e.getMessage());
                } catch (FlightNotFoundException e) {
                        throw new FlightNotFoundException(e.getMessage());
                } catch (SaveToDBException e) {
                        throw new SaveToDBException(e.getMessage());
                } catch (InvalidCredentialsException e) {
                        throw new InvalidCredentialsException(e.getMessage());
                } catch (RuntimeException e) {
                        throw new RuntimeException(e.getMessage());
                } catch (Exception e) {
                        throw new RuntimeException("An error occurred while selling the flight");
                }
        }

        @Override
        public GetFlightTicketResponse addFlight(String token, CreateFlightTicket createFlightTicket) {
                try {
                        String userEmail = jwtService.extractUsername(token);

                        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not found"));

                        if (!user.getEmail().equals("admin@sisal.com")) {
                                throw new AuthorizationException("Unauthorized");
                        }

                        Flight addedFlight = Flight.builder()
                                                .arrivalCity(createFlightTicket.getArrivalCity())
                                                .company(createFlightTicket.getCompany())
                                                .departureCity(createFlightTicket.getDepartureCity())
                                                .departureDay(createFlightTicket.getDepartureDay())
                                                .departureHour(createFlightTicket.getDepartureHour())
                                                .flightNumber(createFlightTicket.getFlightNumber())
                                                .price(createFlightTicket.getPrice())
                                                .build();
        
                        if (addedFlight == null) {
                                throw new RuntimeException("Flight not found");
                        }
        
                        try {
                                flightRepository.save(addedFlight);
                        } catch (SaveToDBException e) {
                                throw new SaveToDBException("An error occurred while saving to the database");
                        }
        
                        return GetFlightTicketResponse.builder()
                                .datetime(LocalDateTime.now())
                                .status(200)
                                .requestId(createFlightTicket.getRequestId())
                                .build();
                        
                } catch (UserNotFoundException e) {
                        throw new UserNotFoundException("User not found");
                } catch (AuthorizationException e) {
                        throw new AuthorizationException("Unauthorized");
                } catch (SaveToDBException e) {
                        throw new SaveToDBException("An error occurred while saving to the database");
                } catch (RuntimeException e) {
                        throw new RuntimeException("Unauthorized");
                } catch (Exception e) {
                        throw new RuntimeException("An error occurred while adding the flight");
                }
        }

        @Override
        public GetUserFlightResponse listUserFlights(String token, String requestId) {
                try {
                        String userEmail = jwtService.extractUsername(token);

                        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not found"));

                        List<String> flightTicketIds = user.getFlightTicketIds();
                        List<Flight> flightList = new ArrayList<>();
                        if(flightTicketIds != null) {
                                flightList = flightRepository.findByIdIn(flightTicketIds).orElseThrow(()-> new FlightNotFoundException("Flight not found"));
                        } else {
                                return GetUserFlightResponse.builder()
                                .datetime(LocalDateTime.now())
                                .status(200)
                                .flights(new ArrayList<>())
                                .requestId(requestId)
                                .build();
                        }

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

                        return GetUserFlightResponse.builder()
                                .datetime(LocalDateTime.now())
                                .status(200)
                                .flights(filterFlightDataList)
                                .requestId(requestId)
                                .build();
                } catch (UserNotFoundException e) {
                        throw new UserNotFoundException(e.getMessage());
                } catch (RuntimeException e) {
                        throw new RuntimeException(e.getMessage());
                } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                }
        }
}
