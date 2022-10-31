package ru.bmstu.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.bmstu.gateway.controller.exception.GatewayErrorException;
import ru.bmstu.gateway.controller.exception.HotelServiceNotAvailableException;

@ControllerAdvice
public class ConrollerAdvisor {
    @ExceptionHandler(HotelServiceNotAvailableException.class)
    public ResponseEntity<?> handleHotelServiceNotAvailableException(HotelServiceNotAvailableException ex) {
        Error err = new Error()
                .setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .setDescription(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(err);
    }

    @ExceptionHandler(GatewayErrorException.class)
    public ResponseEntity<?> handleGatewayErrorException(GatewayErrorException ex) {
        Error err = new Error()
                .setMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .setDescription(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(err);
    }
}
