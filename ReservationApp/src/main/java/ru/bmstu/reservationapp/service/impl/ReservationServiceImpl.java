package ru.bmstu.reservationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.reservationapp.dto.ReservationResponse;
import ru.bmstu.reservationapp.repository.ReservationRepository;
import ru.bmstu.reservationapp.service.ReservationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<ReservationResponse> getReservationsByUsername(String username) {
        return reservationRepository
                .getReservationsByUsername(username)
                .map()
    }
}
