package dev.tugba.flight_ticket_platform.core.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistsUserException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExistsUserWithSameTurkishId(AlreadyExistsUserException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "Validation.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFlightNotFound(FlightNotFoundException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "Runtime.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "Runtime.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationServiceException(AuthenticationServiceException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "Authentication.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<Map<String, Object>> handleGeneralExceptions(Exception ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "General.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "Runtime.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentials(InvalidCredentialsException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "InvalidCredential.Exception", HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SaveToDBException.class)
    public ResponseEntity<Map<String, Object>> handleSaveToDBException(SaveToDBException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "SaveToDB.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TokenCreationException.class)
    public ResponseEntity<Map<String, Object>> handleTokenCreationException(TokenCreationException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(error, "TokenCreation.Exception", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<Map<String, Object>> handleMissingParameterException(MissingParameterException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<>(getErrorsMap(error, "Missing Parameter Exception", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthorizationException(AuthorizationException ex) {
        String error = ex.getMessage();
        return new ResponseEntity<>(getErrorsMap(error, "Authorization Exception", HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    private Map<String, Object > getErrorsMap(String error, String message, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", message != null ? false : true);
        errorResponse.put("status", status.value());
        errorResponse.put("error", error);
        errorResponse.put("message", message);
        errorResponse.put("date", LocalDateTime.now());
        return errorResponse;
    }
}