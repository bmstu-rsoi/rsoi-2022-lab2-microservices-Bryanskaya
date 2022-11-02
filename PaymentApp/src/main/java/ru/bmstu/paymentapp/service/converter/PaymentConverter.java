package ru.bmstu.paymentapp.service.converter;

import ru.bmstu.paymentapp.dto.PaymentInfo;
import ru.bmstu.paymentapp.dto.enums.StatusEnum;
import ru.bmstu.paymentapp.model.PaymentEntity;

public class PaymentConverter {
    public static PaymentInfo fromPaymentEntityToPaymentInfo(PaymentEntity paymentEntity) {
        return new PaymentInfo()
                .setStatus(StatusEnum.valueOf(paymentEntity.getStatus()))
                .setPrice(paymentEntity.getPrice());
    }
}
