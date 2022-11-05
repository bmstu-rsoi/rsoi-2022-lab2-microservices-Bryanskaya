package ru.bmstu.loyaltyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.loyaltyapp.dto.LoyaltyDTO;
import ru.bmstu.loyaltyapp.dto.LoyaltyIntoResponse;
import ru.bmstu.loyaltyapp.dto.enums.StatusEnum;
import ru.bmstu.loyaltyapp.model.LoyaltyEntity;
import ru.bmstu.loyaltyapp.repository.LoyaltyRepository;
import ru.bmstu.loyaltyapp.service.LoyaltyService;
import ru.bmstu.loyaltyapp.service.converter.LoyaltyConverter;


@Service
@RequiredArgsConstructor
public class LoyaltyServiceImpl implements LoyaltyService {
    private final LoyaltyRepository loyaltyRepository;


    @Transactional(readOnly = true)
    public Integer getDiscountByUsername(String username) {
        return loyaltyRepository.getDiscountByUsername(username);
    }

    public Integer getReservationUpdatedPrice(Integer price, Integer discount) {
        return (int)(price * (1 - discount * 0.01));
    }

    @Transactional
    public LoyaltyIntoResponse updateReservationCount(String username) {
        LoyaltyDTO loyaltyDTO = LoyaltyConverter.fromLoyaltyEntityToLoyaltyDTO(loyaltyRepository.getLoyaltyEntityByUsername(username));
        int reservationCount = loyaltyDTO.getReservationCount() + 1;

        if (reservationCount == 20)
            loyaltyDTO.setStatus(loyaltyDTO.getStatus().next());
        else if (reservationCount == 10)
            loyaltyDTO.setStatus(loyaltyDTO.getStatus().next());

        loyaltyDTO.setReservationCount(reservationCount);
        loyaltyDTO.setDiscount(StatusEnum.convert.get(loyaltyDTO.getStatus()));

        LoyaltyDTO res = LoyaltyConverter.fromLoyaltyEntityToLoyaltyDTO(loyaltyRepository
                .saveAndFlush(LoyaltyConverter.fromLoyaltyDTOToLoyaltyEntity(loyaltyDTO)));
        return LoyaltyConverter.fromLoyaltyDTOToLoyaltyInfoResponse(res);
    }
}
