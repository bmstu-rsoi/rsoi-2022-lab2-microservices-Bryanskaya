package ru.bmstu.loyaltyapp.dto.enums;

public enum StatusEnum {
    BRONZE("BRONZE"),
    SILVER("SILVER"),
    GOLD("GOLD");

    private String name;

    StatusEnum(String name) {
        this.name = name;
    }
}
