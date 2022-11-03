package ru.bmstu.loyaltyapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.loyaltyapp.dto.enums.StatusEnum;
import ru.bmstu.loyaltyapp.repository.LoyaltyRepository;
import ru.bmstu.loyaltyapp.service.LoyaltyService;


@Service
@RequiredArgsConstructor
public class LoyaltyServiceImpl implements LoyaltyService {
    private final LoyaltyRepository loyaltyRepository;


    @Transactional(readOnly = true)
    public StatusEnum getStatusByUsername(String username) {
        String status = loyaltyRepository.getStatusByUsername(username);
        return (status == null) ? null : StatusEnum.valueOf(status);
    }
}
