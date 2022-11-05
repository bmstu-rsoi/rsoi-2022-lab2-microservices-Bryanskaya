package ru.bmstu.gateway.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.bmstu.gateway.controller.exception.data.RelatedDataNotFoundException;
import ru.bmstu.gateway.controller.exception.data.RequestDataErrorException;
import ru.bmstu.gateway.controller.exception.data.ReservationByUsernameNotFoundException;
import ru.bmstu.gateway.controller.exception.data.ReservationByUsernameReservationUidNotFoundException;
import ru.bmstu.gateway.controller.exception.service.*;
import ru.bmstu.gateway.dto.*;
import ru.bmstu.gateway.dto.converter.HotelInfoConverter;
import ru.bmstu.gateway.dto.converter.PaymentConverter;
import ru.bmstu.gateway.dto.converter.ReservationConverter;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.UUID;

import static ru.bmstu.gateway.config.AppConfig.*;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class GatewayController {
    @Resource
    private WebClient webClient;


    @GetMapping(value = "/hotels", produces = "application/json")
    public ResponseEntity<?> getHotels(@PathParam(value = "page") Integer page,
                                 @PathParam(value = "size") Integer size) {
        log.info(">>> GATEWAY: Request to get all hotels was caught (params: page={}, size={}).", page, size);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathHotel + "/all")
                        .port(portHotel)
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new HotelServiceNotAvailableException(error.statusCode().toString());
                })
                .toEntity(PaginationResponse.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    @GetMapping(value = "/reservations", produces = "application/json")
    public ResponseEntity<?> getReservationsByUsername(@RequestHeader(value = "X-User-Name") String username) {
        log.info(">>> GATEWAY: Request to get all reservations by username={} was caught.", username);

        ReservationDTO[] reservationArr = _getReservationsArrByUsername(username);
        if (reservationArr == null)
            throw new ReservationByUsernameNotFoundException(username);

        ArrayList<ReservationResponse> reservationResponseList = new ArrayList<>();
        for (ReservationDTO reservation: reservationArr)
            reservationResponseList.add(_getReservationResponse(reservation));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationResponseList);
    }

    @GetMapping(value = "/reservations/{reservationUid}", produces = "application/json")
    public ResponseEntity<?> getReservationsByUsernameReservationUid(@RequestHeader(value = "X-User-Name") String username,
                                                                     @PathVariable(value = "reservationUid") String reservationUid) {
        log.info(">>> GATEWAY: Request to get all reservations by username={} and reservationUid={} was caught.", username, reservationUid);

        ReservationDTO reservation = _getReservationsArrByUsernameReservationUid(username, reservationUid);
        if (reservation == null)
            throw new ReservationByUsernameReservationUidNotFoundException(username, reservationUid);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(_getReservationResponse(reservation));
    }

    private ReservationResponse _getReservationResponse(ReservationDTO reservationDTO) {
        HotelInfo hotelInfo = _getHotelInfoByHotelId(reservationDTO.getHotelId());
        PaymentInfo paymentInfo = _getPaymentInfoByPaymentUid(reservationDTO.getPaymentUid());
        if (hotelInfo == null || paymentInfo == null)
            throw new RelatedDataNotFoundException();

        return ReservationConverter.toReservationResponse(reservationDTO, hotelInfo, paymentInfo);
    }

    private ReservationDTO[] _getReservationsArrByUsername(String username) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathReservation)
                        .port(portHotel)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Name", username)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new ReservationServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(ReservationDTO[].class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private ReservationDTO _getReservationsArrByUsernameReservationUid(String username, String reservationUid) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathReservation + "/{reservationUid}")
                        .port(portHotel)
                        .build(reservationUid))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Name", username)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new ReservationServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(ReservationDTO.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private HotelResponse _getHotelResponseByHotelId(Integer hotelId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathHotel + "/{hotelId}")
                        .port(portHotel)
                        .build(hotelId))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new HotelServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(HotelResponse.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private HotelInfo _getHotelInfoByHotelId(Integer hotelId) {
        return HotelInfoConverter.
                fromHotelResponseToHotelInfo(_getHotelResponseByHotelId(hotelId));
    }

    private PaymentInfo _getPaymentInfoByPaymentUid(UUID paymentUid) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathPayment + "/{paymentUid}")
                        .port(portPayment)
                        .build(paymentUid))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new PaymentServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(PaymentInfo.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity<CreateReservationResponse> postReservation(@RequestHeader(value = "X-User-Name") String username,
                                                                     @RequestBody CreateReservationRequest request) {
        log.info(">>> Request to create reservation was caught (username={}; data={}).", username, request.toString());

        if (!request.isValid())
            throw new RequestDataErrorException(request.toString());

        Integer reservationPrice = _getReservationPrice(username, request);
        PaymentDTO paymentDTO = _postPayment(reservationPrice);
        LoyaltyIntoResponse loyaltyIntoResponse = _updateReservationCount(username);
        ReservationDTO reservationDTO = _postReservation(username, ReservationConverter.toReservationDTO(paymentDTO, request,
                _getHotelIdByHotelUid(request.getHotelUid())));

        CreateReservationResponse createReservationResponse = ReservationConverter
                .fromReservationDTOToCreateReservationResponse(reservationDTO, paymentDTO.getPaymentUid(),
                        loyaltyIntoResponse.getDiscount(), PaymentConverter.fromPaymentDTOToPaymentInfo(paymentDTO));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createReservationResponse);
    }

    private Integer _getReservationPrice(String username, CreateReservationRequest request) {
        Integer reservationPrice = _getReservationFullPrice(username, request);
        Integer discount = _getUserStatus(username);
        return _getReservationUpdatedPrice(reservationPrice, discount);
    }

    private Integer _getReservationFullPrice(String username, CreateReservationRequest request) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathHotel + "/{hotelUid}/price")
                        .port(portHotel)
                        .queryParam("startDate", request.getStartDate())
                        .queryParam("endDate", request.getEndDate())
                        .build(request.getHotelUid()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Name", username)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new ReservationServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(Integer.class)
                .onErrorMap(error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private Integer _getUserStatus(String username) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathLoyalty)
                        .port(portLoyalty)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Name", username)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new LoyaltyServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(Integer.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private Integer _getReservationUpdatedPrice(Integer price, Integer discount) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathLoyalty + "/update")
                        .port(portLoyalty)
                        .queryParam("price", price)
                        .queryParam("discount", discount)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new LoyaltyServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(Integer.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private PaymentDTO _postPayment(Integer price) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(pathPayment)
                        .port(portPayment)
                        .queryParam("price", price)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new PaymentServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(PaymentDTO.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private LoyaltyIntoResponse _updateReservationCount(String username) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(pathLoyalty)
                        .port(portLoyalty)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Name", username)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new LoyaltyServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(LoyaltyIntoResponse.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private Integer _getHotelIdByHotelUid(UUID hotelUid) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(pathHotel + "/{hotelUid}/id")
                        .port(portHotel)
                        .build(hotelUid))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new HotelServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(Integer.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

    private ReservationDTO _postReservation(String username, ReservationDTO reservationDTO) {
        return webClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(pathReservation)
                        .port(portHotel)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-User-Name", username)
                .body(BodyInserters.fromValue(reservationDTO))
                .retrieve()
                .onStatus(HttpStatus::isError, error -> {
                    throw new PaymentServiceNotAvailableException(error.statusCode().toString());
                })
                .bodyToMono(ReservationDTO.class)
                .onErrorMap(Throwable.class, error -> {
                    throw new GatewayErrorException(error.getMessage());
                })
                .block();
    }

}
