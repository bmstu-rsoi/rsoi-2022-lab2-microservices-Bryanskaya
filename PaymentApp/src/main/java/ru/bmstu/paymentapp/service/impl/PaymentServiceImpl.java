package ru.bmstu.paymentapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.paymentapp.dto.PaymentInfo;
import ru.bmstu.paymentapp.repository.PaymentRepository;
import ru.bmstu.paymentapp.service.PaymentService;

import java.util.UUID;

import static ru.bmstu.paymentapp.service.converter.PaymentConverter.fromPaymentEntityToPaymentInfo;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional(readOnly = true)
    public PaymentInfo getPaymentByUid(UUID paymentUid) {
        return fromPaymentEntityToPaymentInfo(paymentRepository.getPaymentByUid(paymentUid));
    }
}