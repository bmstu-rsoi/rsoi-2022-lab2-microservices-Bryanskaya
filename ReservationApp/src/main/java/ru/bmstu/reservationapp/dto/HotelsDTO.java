package ru.bmstu.reservationapp.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Data
@Validated
@Accessors(chain = true)
public class HotelsDTO {
    private Integer id;
    private UUID hotelUid;
    private String name;
    private String country;
    private String city;
    private String address;
    private Integer stars;
    private Integer price;
}