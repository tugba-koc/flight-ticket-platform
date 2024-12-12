package dev.tugba.flight_ticket_platform.entities.concretes;

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
@Document(collection = "flightCollection")
public class Flight {
    @Id
    private String id;

    @Field("flightNumber")
    private String flightNumber;

    @Field("departureCity")
    private String departureCity;

    @Field("arrivalCity")
    private String arrivalCity;

    @Field("company")
    private String company;

    @Field("departureTime")
    private String departureTime;

    @Field("price")
    private Double price;
}
