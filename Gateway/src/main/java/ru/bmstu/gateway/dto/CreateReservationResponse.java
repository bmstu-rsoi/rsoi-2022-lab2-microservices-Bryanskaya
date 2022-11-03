package ru.bmstu.gateway.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import ru.bmstu.gateway.dto.enums.StatusEnum;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Validated
@Accessors(chain = true)
public class CreateReservationResponse {
    private UUID reservationUid;
    private UUID hotelUid;
    private Timestamp startDate;
    private Timestamp endDate;
    private String discount;
    private StatusEnum status;
    private PaymentInfo payment;
}
