package ru.bmstu.gateway.dto;

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

    public boolean isValid() {
        return hotelUid != null && startDate != null && endDate != null;
    }
}
