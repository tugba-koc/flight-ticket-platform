package dev.tugba.flight_ticket_platform.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.tugba.flight_ticket_platform.entities.concretes.User;

public interface UserRepository extends MongoRepository<User, String> {
        Optional<User> findByEmail(String email);
        Optional<User> findByTurkishId(String turkishId);
        boolean existsByEmail(String email);
}
