package ru.bmstu.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppParams {
    @Value(value = "${path.service.hotel}")
    public String pathHotel;
    @Value(value = "${path.service.loyalty}")
    public String pathLoyalty;
    @Value(value = "${path.service.reservation}")
    public String pathReservation;
    @Value(value = "${path.service.payment}")
    public String pathPayment;

    @Value(value = "${port.service.hotel}")
    public String portHotel;
    @Value(value = "${port.service.loyalty}")
    public String portLoyalty;
    @Value(value = "${port.service.payment}")
    public String portPayment;
}
