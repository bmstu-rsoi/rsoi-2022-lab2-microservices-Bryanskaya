package ru.bmstu.reservationapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.bmstu.reservationapp.dto.HotelsDTO;


public interface HotelsService {
    Page<HotelsDTO> getHotels(Pageable pageable);
}
