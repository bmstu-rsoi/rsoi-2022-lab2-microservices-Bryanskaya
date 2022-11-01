package ru.bmstu.reservationapp.service.converter;

import ru.bmstu.reservationapp.dto.HotelResponse;
import ru.bmstu.reservationapp.model.HotelsEntity;

public class HotelConverter {
    public static HotelResponse fromHotelsEntityToHotelResponse(HotelsEntity hotelsEntity) {
        return new HotelResponse()
                .setHotelUid(hotelsEntity.getHotelUid())
                .setName(hotelsEntity.getName())
                .setCountry(hotelsEntity.getCountry())
                .setCity(hotelsEntity.getCity())
                .setAddress(hotelsEntity.getAddress())
                .setStars(hotelsEntity.getStars())
                .setPrice(hotelsEntity.getPrice());
    }
}
