package ru.bmstu.gateway.controller.exception.service;

public class ReservationServiceNotAvailableException extends RuntimeException {
    public static String MSG = "GATEWAY: Reservation service is not available, code=%s.";

    public ReservationServiceNotAvailableException(String codeStatus) {
        super(String.format(MSG, codeStatus));
    }

}
