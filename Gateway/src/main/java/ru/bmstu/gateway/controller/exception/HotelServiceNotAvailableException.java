package ru.bmstu.gateway.controller.exception;


public class HotelServiceNotAvailableException extends RuntimeException {
    public static String MSG = "GATEWAY: Hotel service is not available, code=%s.";

    public HotelServiceNotAvailableException(String codeStatus) {
        super(String.format(MSG, codeStatus));
    }
}
