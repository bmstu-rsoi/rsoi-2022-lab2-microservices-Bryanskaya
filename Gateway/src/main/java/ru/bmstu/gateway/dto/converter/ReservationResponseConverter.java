package ru.bmstu.gateway.dto.converter;

import ru.bmstu.gateway.dto.HotelInfo;
import ru.bmstu.gateway.dto.PaymentInfo;
import ru.bmstu.gateway.dto.ReservationDTO;
import ru.bmstu.gateway.dto.ReservationResponse;

public class ReservationResponseConverter {
    public static ReservationResponse toReservationResponse(ReservationDTO reservationDTO,
                                                            HotelInfo hotelInfo,
                                                            PaymentInfo paymentInfo) {
        return new ReservationResponse()
                .setReservationUid(reservationDTO.getReservationUid())
                .setHotel(hotelInfo)
                .setStartDate(reservationDTO.getStartDate())
                .setEndDate(reservationDTO.getEndDate())
                .setStatus(reservationDTO.getStatus())
                .setPayment(paymentInfo);
    }
}

// todo other entities
