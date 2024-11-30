package dev.tugba.flight_ticket_platform.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
@Document(collection = "userCollection")
public class User {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("surname")
    private String surname;

    @Field("turkishId")
    private String turkishId;

    @Field("email")
    private String email;

    @Field("phoneNumber")
    private String phoneNumber;

    @Field("password")
    private String password;

    @Field("roles")
    private List<String> roles;

    @Field("createdAt")
    private LocalDateTime createdAt;

    @Field("updatedAt")
    private LocalDateTime updatedAt;

    @Field("gender")
    private String gender;
}
