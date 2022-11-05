package ru.bmstu.loyaltyapp.service;

import ru.bmstu.loyaltyapp.dto.LoyaltyIntoResponse;
import ru.bmstu.loyaltyapp.dto.enums.StatusEnum;


public interface LoyaltyService {
    Integer getDiscountByUsername(String username);
    Integer getReservationUpdatedPrice(Integer price, Integer discount);
    LoyaltyIntoResponse updateReservationCount(String username);
    LoyaltyIntoResponse cancelReservationCount(String username);
}
