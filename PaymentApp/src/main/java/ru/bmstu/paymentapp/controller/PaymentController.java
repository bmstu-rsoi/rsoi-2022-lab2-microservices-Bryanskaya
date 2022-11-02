package ru.bmstu.paymentapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.paymentapp.service.PaymentService;

import java.util.UUID;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping(value = "/{paymentUid}", produces = "application/json")
    public ResponseEntity<?> getPaymentByUid(@PathVariable UUID paymentUid) {
        log.info(">>> PAYMENT: Request to get payment by paymentUid={} was caught.", paymentUid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentService.getPaymentByUid(paymentUid));
    }
}
