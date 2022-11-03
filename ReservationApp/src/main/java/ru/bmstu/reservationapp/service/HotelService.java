package ru.bmstu.reservationapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bmstu.reservationapp.dto.HotelResponse;

import java.util.UUID;


public interface HotelService {
    Page<HotelResponse> getHotels(Pageable pageable);
    HotelResponse getHotelByHotelId(Integer hotelId);
    HotelResponse getHotelByHotelUid(UUID hotelUid);
}
