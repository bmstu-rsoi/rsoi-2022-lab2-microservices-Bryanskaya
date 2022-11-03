package ru.bmstu.reservationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.reservationapp.dto.CreateReservationRequest;
import ru.bmstu.reservationapp.dto.HotelResponse;
import ru.bmstu.reservationapp.dto.ReservationDTO;
import ru.bmstu.reservationapp.model.ReservationEntity;
import ru.bmstu.reservationapp.repository.HotelRepository;
import ru.bmstu.reservationapp.repository.ReservationRepository;
import ru.bmstu.reservationapp.service.HotelService;
import ru.bmstu.reservationapp.service.ReservationService;
import ru.bmstu.reservationapp.service.converter.ReservationConverter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final HotelService hotelService;

    @Transactional(readOnly = true)
    public List<ReservationDTO> getReservationsByUsername(String username) {
        return reservationRepository
                .getReservationsByUsername(username)
                .stream()
                .map(ReservationConverter::fromReservationEntityToReservationResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReservationDTO getReservationsByUsernameReservationUid(String username, UUID reservationUid) {
        ReservationEntity reservationEntity = reservationRepository.getReservationsByUsernameReservationUid(username, reservationUid);
        return (reservationEntity == null) ? null : ReservationConverter.fromReservationEntityToReservationResponse(reservationEntity);
    }

    @Transactional
    public ReservationDTO createReservation(String username, CreateReservationRequest request) {
        HotelResponse hotelResponse = hotelService.getHotelByHotelUid(request.getHotelUid());
        if (hotelResponse == null)
            return null;

        // todo
    }
}
