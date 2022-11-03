package ru.bmstu.gateway.controller.exception;

public class ReservationByUsernameReservationUidNotFoundException extends RuntimeException {
    public static String MSG = "GATEWAY: reservation for username=%s reservationUid=%s was not found.";

    public ReservationByUsernameReservationUidNotFoundException(String username, String reservationUid) {
        super(String.format(MSG, username, reservationUid));
    }
}