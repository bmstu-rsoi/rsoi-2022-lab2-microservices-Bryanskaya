package ru.bmstu.reservationapp.service;

import ru.bmstu.reservationapp.dto.ReservationResponse;

import java.util.List;

public interface ReservationService {
    List<ReservationResponse> getReservationsByUsername(String username);
}
