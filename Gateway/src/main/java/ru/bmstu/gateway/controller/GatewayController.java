package ru.bmstu.gateway.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.bmstu.gateway.controller.exception.GatewayErrorException;
import ru.bmstu.gateway.controller.exception.HotelServiceNotAvailable;
import ru.bmstu.gateway.dto.PaginationResponse;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class GatewayController {
    @Resource
    private WebClient webClient;

    @GetMapping(value = "/hotels", produces = "application/json")
    public ResponseEntity getHotels(@PathParam(value = "page") Integer page,
                                 @PathParam(value = "size") Integer size) {
        log.info(">>> GATEWAY: Request to get all hotels was caught.");

        String temp = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/v1/hotels")
                        .port("8070")
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new HotelServiceNotAvailable(error.statusCode().toString());
                })
                .bodyToMono(String.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
        log.info("//////////// {}", temp);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(temp);
    }
}
