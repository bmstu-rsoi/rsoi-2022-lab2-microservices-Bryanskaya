package ru.bmstu.reservationapp.service.converter;

import ru.bmstu.reservationapp.dto.HotelsDTO;
import ru.bmstu.reservationapp.model.HotelsEntity;

public class HotelsConverter {
    public static HotelsDTO fromHotelsEntityToHotelsDTO(HotelsEntity hotelsEntity) {
        return new HotelsDTO()
                .setHotelUid(hotelsEntity.getHotelUid())
                .setName(hotelsEntity.getName())
                .setCountry(hotelsEntity.getCountry())
                .setCity(hotelsEntity.getCity())
                .setAddress(hotelsEntity.getAddress())
                .setStars(hotelsEntity.getStars())
                .setPrice(hotelsEntity.getPrice());
    }
}
