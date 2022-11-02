package ru.bmstu.paymentapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.paymentapp.dto.enums.StatusEnum;
import ru.bmstu.paymentapp.service.PaymentService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping(value = "/{paymentUid}", produces = "application/json")
    public ResponseEntity<?> getPaymentByUid(@PathVariable)

}
