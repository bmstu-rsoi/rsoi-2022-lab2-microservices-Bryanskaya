package ru.bmstu.reservationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Validated
@Accessors(chain = true)
public class CreateReservationRequest {
    private UUID hotelUid;
    private Timestamp startDate;
    private Timestamp endDate;
}