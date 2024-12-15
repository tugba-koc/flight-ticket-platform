package dev.tugba.flight_ticket_platform.entities.concretes;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import dev.tugba.flight_ticket_platform.auth.config.constants.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "userCollection")
public class User implements UserDetails {
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

    @Field("flightTicketIds")
    private List<String> flightTicketIds;

    private Role role;

    @Field("createdAt")
    private LocalDateTime createdAt;

    @Field("updatedAt")
    private LocalDateTime updatedAt;

    @Field("balance")
    private Double balance;

    @Field("gender")
    private String gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return email;
    }
}
