package ru.bmstu.reservationapp.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.reservationapp.dto.CreateReservationRequest;
import ru.bmstu.reservationapp.service.ReservationService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getReservationsByUsername(@RequestHeader(value = "X-User-Name") String username) {
        log.info(">>> RESERVATION: Request to get all reservations by username={} was caught.", username);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationService.getReservationsByUsername(username));
    }

    @GetMapping(value = "/{reservationUid}", produces = "application/json")
    public ResponseEntity<?> getReservationsByUsernameReservationUid(@RequestHeader(value = "X-User-Name") String username,
                                                                     @PathVariable(value = "reservationUid") String reservationUid) {
        log.info(">>> RESERVATION: Request to get all reservations by username and reservationUid was caught.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationService.getReservationsByUsernameReservationUid(username,
                        UUID.fromString(reservationUid)));
    }

    @PostMapping
    public void createReservation(@RequestHeader(value = "X-User-Name") String username,
                                @RequestBody CreateReservationRequest request) {
        log.info(">>> Request to create reservation was caught (username={}; data={}).", username, request.toString());


    }
}
