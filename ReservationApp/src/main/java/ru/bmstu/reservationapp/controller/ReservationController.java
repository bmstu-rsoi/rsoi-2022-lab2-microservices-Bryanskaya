package ru.bmstu.reservationapp.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.reservationapp.dto.ReservationResponse;
import ru.bmstu.reservationapp.service.ReservationService;

import javax.websocket.server.PathParam;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getReservationsByUsername(@RequestHeader(value = "X-User-Name") String username) {
        log.info(">>> RESERVATION: Request to get all reservations by username was caught.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationService.getReservationsByUsername(username));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getReservationsByUsernameReservationUid(@RequestHeader(value = "X-User-Name") String username,
                                                                     @PathParam(value = "reservationUid") String reservationUId) {
        log.info(">>> RESERVATION: Request to get all reservations by username and reservationUid was caught.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationService.getReservationsByUsernameReservationUid(username,
                        UUID.fromString(reservationUId)));
    }
}
