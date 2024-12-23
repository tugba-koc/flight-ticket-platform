package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistsUserException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExistsUserWithSameTurkishId(AlreadyExistsUserException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Validation.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFlightNotFound(FlightNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Runtime.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Runtime.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationServiceException(AuthenticationServiceException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Authentication.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<Map<String, Object>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "General.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Runtime.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "InvalidCredential.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SaveToDBException.class)
    public ResponseEntity<Map<String, Object>> handleSaveToDBException(SaveToDBException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "SaveToDB.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenCreationException.class)
    public ResponseEntity<Map<String, Object>> handleTokenCreationException(TokenCreationException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "TokenCreation.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Map<String, Object>> handleMalformedJwtException(MalformedJwtException ex) {
        System.out.println("geldi");
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors, "Malformed JWT Exception", HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, Object>> handleExpiredJwtException(ExpiredJwtException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors, "Expired JWT Exception", HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingParameterException(MissingParameterException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors, "Missing Parameter Exception", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthorizationException(AuthorizationException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors, "Authorization Exception", HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    private Map<String, Object > getErrorsMap(List<String> errors, String message, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", message != null ? false : true);
        errorResponse.put("status", status.value());
        errorResponse.put("errors", errors);
        errorResponse.put("message", message);
        errorResponse.put("date", LocalDateTime.now());
        return errorResponse;
    }
}