package ru.bmstu.gateway.dto.enums;

public enum StatusEnum {
    PAID("PAID"),
    REVERSED("REVERSED"),
    CANCELLED("CANCELLED");

    private String name;

    StatusEnum(String name) {
        this.name = name;
    }
}
