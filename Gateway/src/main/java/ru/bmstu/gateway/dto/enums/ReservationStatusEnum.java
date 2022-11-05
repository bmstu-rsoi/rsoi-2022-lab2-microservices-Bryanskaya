package ru.bmstu.gateway.dto.enums;

public enum ReservationStatusEnum {
    PAID("PAID"),
    REVERSED("REVERSED"),
    CANCELLED("CANCELLED");

    private String name;

    ReservationStatusEnum(String name) {
        this.name = name;
    }
}
