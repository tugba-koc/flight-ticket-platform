package dev.tugba.flight_ticket_platform.business.concretes;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.tugba.flight_ticket_platform.auth.config.abstracts.JwtService;
import dev.tugba.flight_ticket_platform.auth.config.constants.Role;
import dev.tugba.flight_ticket_platform.business.abstracts.AuthenticateService;
import dev.tugba.flight_ticket_platform.business.requests.CreateForgotPasswordRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateForgotPasswordUpdateRequest;
import dev.tugba.flight_ticket_platform.business.requests.CreateRegisterRequest;
import dev.tugba.flight_ticket_platform.business.requests.LoginRequest;
import dev.tugba.flight_ticket_platform.business.requests.UpdatePassword;
import dev.tugba.flight_ticket_platform.business.responses.GetForgotPasswordCheckResponse;
import dev.tugba.flight_ticket_platform.business.responses.GetResetPasswordResponse;
import dev.tugba.flight_ticket_platform.business.responses.LoginResponse;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.AlreadyExistsUserException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.AuthenticationServiceException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.InvalidCredentialsException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.MissingParameterException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.SaveToDBException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.TokenCreationException;
import dev.tugba.flight_ticket_platform.core.utilities.exceptions.UserNotFoundException;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.TokenRepository;
import dev.tugba.flight_ticket_platform.dataAccess.abstracts.UserRepository;
import dev.tugba.flight_ticket_platform.entities.concretes.Token;
import dev.tugba.flight_ticket_platform.entities.concretes.User;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticateManager implements AuthenticateService {

        private final UserRepository userRepository;
        private final TokenRepository tokenRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        @Override
        public String createUser(CreateRegisterRequest createRegisterRequest) {
                try {
                        // check if the user exists
                        if (userRepository.existsByEmail(createRegisterRequest.getEmail())) {
                                throw new AlreadyExistsUserException("The user already exists");
                        } else {
                                // save user
                                try {
                                        userRepository.save(User.builder()
                                        .email(createRegisterRequest.getEmail())
                                        .password(passwordEncoder.encode(createRegisterRequest.getPassword()))
                                        .turkishId(createRegisterRequest.getTurkishId())
                                        .name(createRegisterRequest.getName())
                                        .surname(createRegisterRequest.getSurname())
                                        .phoneNumber(createRegisterRequest.getPhoneNumber())
                                        .role(Role.VISITOR)
                                        .birthDate(createRegisterRequest.getBirthDate())
                                        .gender(createRegisterRequest.getGender())
                                        .createdAt(java.time.LocalDateTime.now())
                                        .updatedAt(java.time.LocalDateTime.now())
                                        .balance(0.0)
                                        .build());
                                
                                String saved = "saved";
                                return saved;
                                        
                                } catch (SaveToDBException e) {
                                        throw new SaveToDBException("An unexpected error occurred while saving to the database");
                                }
                        }
                } catch (AlreadyExistsUserException e) {
                        throw new AlreadyExistsUserException(e.getMessage());
                } catch (SaveToDBException e) {
                        throw new SaveToDBException(e.getMessage());
                }
        }

        @Override
        public LoginResponse login(LoginRequest loginRequest) {
            try {
                // find user
                User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("User not found, please register"));
        
                // check password
                if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                    throw new InvalidCredentialsException("Accountcode and password do not match");
                }
        
                // authenticate user
                try {
                    authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword())
                    );
                } catch (AuthenticationServiceException e) {
                    throw new AuthenticationServiceException("Authentication failed for provided credentials");
                }
        
                // generate token
                String token;
                try {
                    token = jwtService.generateToken(user);
                } catch (TokenCreationException e) {
                    throw new TokenCreationException("Failed to generate token");
                }
        
                // save token
                tokenRepository.save(Token.builder()
                    .token(token)
                    .userId(user.getId())
                    .valid(true)
                    .createdAt(java.time.LocalDateTime.now())
                    .build()
                );
        
                String role = user.getRole() == Role.ADMIN ? "ADMIN" : "VISITOR";
        
                return LoginResponse.builder()
                    .status(200)
                    .token(token)
                    .datetime(LocalDateTime.now())
                    .requestId(loginRequest.getRequestId())
                    .role(role)
                    .build();
        
            } catch (UserNotFoundException e) {
                throw new UserNotFoundException(e.getMessage());
            } catch (InvalidCredentialsException e) {
                throw new InvalidCredentialsException(e.getMessage());
            } catch (AuthenticationServiceException e) {
                throw new AuthenticationServiceException(e.getMessage());
            } catch (TokenCreationException e) {
                throw new TokenCreationException(e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException("An unexpected error occurred");
            }
        }

        @Override
        public GetResetPasswordResponse resetPassword(String bearerToken, UpdatePassword updatePassword) {
                try {
                        if(updatePassword.getPassword().isEmpty() || updatePassword.getNewPassword().isEmpty()) {
                                throw new MissingParameterException("Password fields cannot be empty");
                        };
                        if (!updatePassword.getNewPassword().equals(updatePassword.getPassword())) {
                                throw new InvalidCredentialsException("Passwords do not match");
                        };
                        String userEmail = jwtService.extractUsername(bearerToken);
                
                        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("User not found"));
        
                        user.setUpdatedAt(LocalDateTime.now());
                        if (!updatePassword.getNewPassword().isEmpty()){
                                user.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
                        } else {
                                throw new RuntimeException("New password is required");
                        }
        
                        try {
                                userRepository.save(user);
                        } catch (SaveToDBException e) {
                                throw new SaveToDBException("An unexpected error occurred while saving to the database");
                        }
                        return GetResetPasswordResponse.builder()
                                .status(200)
                                .requestId(updatePassword.getRequestId())
                                .datetime(LocalDateTime.now())
                                .build();
                } catch (MissingParameterException e) {
                        throw new MissingParameterException(e.getMessage());
                } catch (InvalidCredentialsException e) {
                        throw new InvalidCredentialsException(e.getMessage());
                } catch (UserNotFoundException e) {
                        throw new UserNotFoundException(e.getMessage());
                } catch (SaveToDBException e) {
                        throw new SaveToDBException(e.getMessage());
                } catch (Exception e) {
                        throw new RuntimeException("An unexpected error occurred");
                }
        }

        @Override
        public GetForgotPasswordCheckResponse forgotPasswordCheck(
                        CreateForgotPasswordRequest createForgotPasswordRequest) {
                try {
                        User user = userRepository.findByEmail(createForgotPasswordRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found"));
                        if (!user.getPhoneNumber().equals(createForgotPasswordRequest.getPhoneNumber())) {
                                throw new InvalidCredentialsException("Phone number does not match");
                        }
                        if (!user.getBirthDate().equals(createForgotPasswordRequest.getBirthDate())) {
                                System.out.println("User birthdate: " + user.getBirthDate());
                                System.out.println("Request birthdate: " + createForgotPasswordRequest.getBirthDate());
                                throw new InvalidCredentialsException("Birthdate does not match");
                        }
                        return GetForgotPasswordCheckResponse.builder()
                                .status(200)
                                .requestId(createForgotPasswordRequest.getRequestId())
                                .datetime(LocalDateTime.now())
                                .build();

                } catch (UserNotFoundException e) {
                        throw new UserNotFoundException("User not found");
                } catch (InvalidCredentialsException e) {
                        throw new InvalidCredentialsException(e.getMessage());
                } catch (Exception e) {
                        throw new RuntimeException("An unexpected error occurred", e);
                }
        }

        @Override
        public GetForgotPasswordCheckResponse forgotPasswordUpdate(
                        CreateForgotPasswordUpdateRequest createForgotPasswordUpdateRequest) {
                try {
                        if(createForgotPasswordUpdateRequest.getNewPassword() != createForgotPasswordUpdateRequest.getConfirmPassword()) {
                                throw new InvalidCredentialsException("Passwords do not match");
                        }

                        User user = userRepository.findByEmail(createForgotPasswordUpdateRequest.getEmail()).orElseThrow(()-> new UserNotFoundException("User not found"));

                        if(!user.getPhoneNumber().equals(createForgotPasswordUpdateRequest.getPhoneNumber())) {
                                throw new InvalidCredentialsException("Phone number does not match");
                        }
                        if(!user.getBirthDate().equals(createForgotPasswordUpdateRequest.getBirthDate())) {
                                System.out.println("User birthdate: " + user.getBirthDate());
                                System.out.println("Request birthdate: " + createForgotPasswordUpdateRequest.getBirthDate());
                                throw new InvalidCredentialsException("Birthdate does not match");
                        }
                        user.setPassword(passwordEncoder.encode(createForgotPasswordUpdateRequest.getNewPassword()));
                        user.setUpdatedAt(LocalDateTime.now());

                        return GetForgotPasswordCheckResponse.builder()
                                .status(200)
                                .requestId(createForgotPasswordUpdateRequest.getRequestId())
                                .datetime(LocalDateTime.now())
                                .build();
                        
                } catch (UserNotFoundException e) {
                        throw new UserNotFoundException(e.getMessage());
                } catch (InvalidCredentialsException e) {
                        throw new InvalidCredentialsException(e.getMessage());
                } catch (Exception e) {
                        throw new RuntimeException("An unexpected error occurred", e);
                }
        }
}
