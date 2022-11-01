package ru.bmstu.gateway.dto.converter;

import ru.bmstu.gateway.dto.HotelInfo;
import ru.bmstu.gateway.dto.HotelResponse;

public class HotelInfoConverter {
    public static HotelInfo fromHotelResponseToHotelInfo(HotelResponse hotelResponse) {
        return new HotelInfo()
                .setHotelUid(hotelResponse.getHotelUid())
                .setName(hotelResponse.getName())
                .setFullAddress(hotelResponse.getAddress())
                .setStars(hotelResponse.getStars());
    }
}
