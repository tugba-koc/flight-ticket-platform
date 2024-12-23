package dev.tugba.flight_ticket_platform.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.tugba.flight_ticket_platform.entities.concretes.Flight;

public interface FlightRepository extends MongoRepository<Flight, String> {
        Optional<List<Flight>> findByDepartureCityAndArrivalCityAndDepartureDay(String departureCity, String arrivalCity, String departureDay);
        Optional<List<Flight>> findByIdIn(List<String> flightIds);
}
