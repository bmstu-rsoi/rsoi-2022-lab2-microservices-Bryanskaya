package ru.bmstu.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class AppConfig {
    @Value(value = "${path.service.hotel}")
    public static String pathHotel;
    @Value(value = "${path.service.loyalty}")
    public static String pathLoyalty;
    @Value(value = "${path.service.reservation}")
    public static String pathReservation;
    @Value(value = "${path.service.payment}")
    public static String pathPayment;

    @Value(value = "${port.service.hotel}")
    public static String portHotel;
    @Value(value = "${port.service.loyalty}")
    public static String portLoyalty;
    @Value(value = "${port.service.payment}")
    public static String portPayment;
    
    @Bean
    public WebClient webClient()  {
        return WebClient
                .create("http://localhost");
    }
}
