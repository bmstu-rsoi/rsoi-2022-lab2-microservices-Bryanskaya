package ru.bmstu.reservationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.reservationapp.dto.HotelResponse;
import ru.bmstu.reservationapp.repository.HotelRepository;
import ru.bmstu.reservationapp.service.HotelService;
import ru.bmstu.reservationapp.service.converter.HotelConverter;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Transactional(readOnly = true)
    public Page<HotelResponse> getHotels(Pageable pageable) {
        return hotelRepository
                .findAll(pageable)
                .map(HotelConverter::fromHotelsEntityToHotelResponse);
    }

    @Transactional(readOnly = true)
    public HotelResponse getHotelByHotelId(Integer hotelId) {
        return HotelConverter.fromHotelsEntityToHotelResponse(hotelRepository
                .getHotelById(hotelId));
    }

    @Transactional(readOnly = true)
    public HotelResponse getHotelByHotelUid(UUID hotelUid) {
        return HotelConverter.fromHotelsEntityToHotelResponse(hotelRepository
                .getHotelByUid(hotelUid));
    }
}
