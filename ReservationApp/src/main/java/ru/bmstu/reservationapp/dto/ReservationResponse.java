package ru.bmstu.reservationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;
import ru.bmstu.reservationapp.dto.enums.StatusEnum;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Validated
@Accessors(chain = true)
public class ReservationResponse {
    private UUID reservationUid;
    private Integer hotelId;
    private Timestamp startDate;
    private Timestamp endDate;
    private StatusEnum status;
    private UUID paymentUid;
}
