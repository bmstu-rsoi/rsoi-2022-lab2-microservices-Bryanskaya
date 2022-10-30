package ru.bmstu.gateway.controller.exception;


public class HotelServiceNotAvailable extends RuntimeException {
    public static String MSG = "GATEWAY: Hotel service is not available, code=%s.";

    public HotelServiceNotAvailable(String code) {
        super(String.format(MSG, code));
    }
}
