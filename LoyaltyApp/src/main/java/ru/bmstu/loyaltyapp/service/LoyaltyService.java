package ru.bmstu.loyaltyapp.service;

import ru.bmstu.loyaltyapp.dto.enums.StatusEnum;


public interface LoyaltyService {
    StatusEnum getStatusByUsername(String username);
}
