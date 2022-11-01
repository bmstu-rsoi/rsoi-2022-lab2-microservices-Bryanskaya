package ru.bmstu.reservationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.reservationapp.dto.ReservationResponse;
import ru.bmstu.reservationapp.model.ReservationEntity;
import ru.bmstu.reservationapp.repository.ReservationRepository;
import ru.bmstu.reservationapp.service.ReservationService;
import ru.bmstu.reservationapp.service.converter.ReservationConverter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<ReservationResponse> getReservationsByUsername(String username) {
        return reservationRepository
                .getReservationsByUsername(username)
                .stream()
                .map(ReservationConverter::fromReservationEntityToReservationResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReservationResponse getReservationsByUsernameReservationUid(String username, UUID reservationUid) {
        ReservationEntity reservationEntity = reservationRepository.getReservationsByUsernameReservationUid(username, reservationUid);
        return ReservationConverter.fromReservationEntityToReservationResponse(reservationEntity);
    }
}
