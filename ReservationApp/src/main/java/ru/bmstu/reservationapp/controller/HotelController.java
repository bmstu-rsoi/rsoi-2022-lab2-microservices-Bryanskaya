package ru.bmstu.reservationapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.reservationapp.controller.converter.ResponseConverter;
import ru.bmstu.reservationapp.dto.HotelResponse;
import ru.bmstu.reservationapp.dto.PaginationResponse;
import ru.bmstu.reservationapp.service.HotelService;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hotels")
public class HotelController {
    private final HotelService hotelsService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<PaginationResponse> getHotels(@PathParam(value = "page") Integer page,
                                                     @PathParam(value = "size") Integer size) {
        log.info(">>> RESERVATION: Request to get all hotels was caught.");

        Pageable paging = PageRequest.of(page - 1, size);
        Page<HotelResponse> hotelsResponsePage = hotelsService.getHotels(paging);

        PaginationResponse paginationResponse = ResponseConverter.toPaginationResponse(page, size, hotelsResponsePage);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paginationResponse);
    }
}
