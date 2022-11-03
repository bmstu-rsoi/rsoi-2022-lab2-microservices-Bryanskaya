package ru.bmstu.loyaltyapp.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.loyaltyapp.service.LoyaltyService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/loyalty")
public class LoyaltyController {
    private final LoyaltyService loyaltyService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getStatusByUsername(@RequestHeader(value = "X-User-Name") String username) {
        log.info(">>> LOYALTY: Request to get username's={} loyalty status was caught.", username);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loyaltyService.getStatusByUsername(username));
    }
}
