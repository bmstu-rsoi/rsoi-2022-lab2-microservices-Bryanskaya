package ru.bmstu.reservationapp.service.converter;

import ru.bmstu.reservationapp.dto.HotelInfo;
import ru.bmstu.reservationapp.dto.ReservationResponse;
import ru.bmstu.reservationapp.dto.enums.StatusEnum;
import ru.bmstu.reservationapp.model.ReservationEntity;

import java.sql.Timestamp;
import java.util.UUID;

public class ReservationConverter {
    public static ReservationResponse fromReservationEntityToReservationResponse(ReservationEntity reservationEntity) {
        return new ReservationResponse()
                .setReservationUid(reservationEntity.getReservationUid())
                .setHotelId(reservationEntity.getHotelId())
                .setStartDate(reservationEntity.getStartDate())
                .setEndDate(reservationEntity.getEndDate())
                .setStatus(reservationEntity.getStatus())
                .setReservationUid(reservationEntity.getPaymentUid());
    }
}
