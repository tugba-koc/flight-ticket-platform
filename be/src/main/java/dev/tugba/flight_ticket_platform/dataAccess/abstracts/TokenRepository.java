package dev.tugba.flight_ticket_platform.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.tugba.flight_ticket_platform.entities.concretes.Token;

public interface TokenRepository extends MongoRepository<Token, String>  {
        boolean existsByUserId(String userId);
        Optional<Token> deleteByUserId(String userId);
        Optional<Token> findByToken(String accessToken);
}
