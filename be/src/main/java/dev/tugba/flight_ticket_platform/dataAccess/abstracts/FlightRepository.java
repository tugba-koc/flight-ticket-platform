package dev.tugba.flight_ticket_platform.dataAccess.abstracts;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.tugba.flight_ticket_platform.entities.concretes.Flight;

public interface FlightRepository extends MongoRepository<Flight, String> {
        /* Optional<Flight> findById(int id); */
}
