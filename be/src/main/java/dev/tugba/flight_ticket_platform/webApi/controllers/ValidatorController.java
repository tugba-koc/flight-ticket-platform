package dev.tugba.flight_ticket_platform.webApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.tugba.flight_ticket_platform.business.abstracts.ValidatorService;
import dev.tugba.flight_ticket_platform.business.responses.GetValidateResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/validator")
@AllArgsConstructor
public class ValidatorController {

        @Autowired
        private ValidatorService validatorService;
        
        @GetMapping("/validateEmail")
        @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
        public ResponseEntity<GetValidateResponse> validateEmail(@RequestParam("requestId") String requestId, @RequestParam("email") String email) {
                return ResponseEntity.ok(validatorService.validateEmail(requestId, email));
        }
        
        @GetMapping("/validateTurkishId")
        @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
        public ResponseEntity<GetValidateResponse> validateTurkishId(@RequestParam("requestId") String requestId, @RequestParam("tcId") String tcId) {
                return ResponseEntity.ok(validatorService.validateTurkishId(requestId, tcId));
        }

        @GetMapping("/validatePhoneNumber")
        @CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
        public ResponseEntity<GetValidateResponse> validatePhoneNumber(@RequestParam("requestId") String requestId, @RequestParam("phoneNumber") String phoneNumber) {
                return ResponseEntity.ok(validatorService.validatePhoneNumber(requestId, phoneNumber));
        }
}
