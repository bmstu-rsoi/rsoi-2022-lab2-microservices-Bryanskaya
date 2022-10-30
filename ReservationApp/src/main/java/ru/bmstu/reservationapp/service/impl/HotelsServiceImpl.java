package ru.bmstu.reservationapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.reservationapp.dto.HotelsDTO;
import ru.bmstu.reservationapp.repository.HotelsRepository;
import ru.bmstu.reservationapp.service.HotelsService;
import ru.bmstu.reservationapp.service.converter.HotelsConverter;


@Service
@RequiredArgsConstructor
public class HotelsServiceImpl implements HotelsService {
    private final HotelsRepository hotelsRepository;

    @Transactional(readOnly = true)
    public Page<HotelsDTO> getHotels(Pageable pageable) {
        return hotelsRepository.findAll(pageable)
                .map(HotelsConverter::fromHotelsEntityToHotelsDTO);
    }
}
