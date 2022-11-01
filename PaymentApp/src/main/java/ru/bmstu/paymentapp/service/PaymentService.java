package ru.bmstu.paymentapp.service;

import ru.bmstu.paymentapp.dto.PaymentInfo;

import java.util.UUID;

public interface PaymentService {
    PaymentInfo getPaymentByUid(UUID paymentUid);
}
