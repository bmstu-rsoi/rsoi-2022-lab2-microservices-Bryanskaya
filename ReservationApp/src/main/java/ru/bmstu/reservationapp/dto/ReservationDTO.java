package ru.bmstu.reservationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Validated
@Accessors(chain = true)
public class ReservationDTO {
    private Integer id;
    private UUID reservationUid;
    private String username;
    private UUID paymentUid;
    private Integer hotelId;
    private String status;
    private Timestamp startDate;
    private Timestamp endDate;
}
