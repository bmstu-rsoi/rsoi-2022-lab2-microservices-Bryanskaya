package ru.bmstu.reservationapp.service;

import ru.bmstu.reservationapp.dto.ReservationResponse;

import java.util.List;
import java.util.UUID;

public interface ReservationService {
    List<ReservationResponse> getReservationsByUsername(String username);
    ReservationResponse getReservationsByUsernameReservationUid(String username, UUID reservationUid);
}
