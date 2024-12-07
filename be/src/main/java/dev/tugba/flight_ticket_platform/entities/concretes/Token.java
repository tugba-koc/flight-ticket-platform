package dev.tugba.flight_ticket_platform.entities.concretes;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tokenCollection")
public class Token {
    @Id
    private String id;

    @Field("token")
    private String token;

    @Field("userId")
    private String userId;

    @Field("valid")
    private boolean valid;

    @Field("createdAt")
    private LocalDateTime createdAt;
}
