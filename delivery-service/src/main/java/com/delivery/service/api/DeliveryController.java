package com.delivery.service.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/deliver")
public class DeliveryController {

    /***
     * This is just to test consul discovery with open feign in test containers
     * a real scenario would check who are the driver that are available, and close to the delivery path
     * and assign this orderId approved to this driver
     */

    @GetMapping("/{orderId}")
    public ResponseEntity<DeliveryAssignedResponse> assignOrder(@PathVariable String orderId) {
        return ResponseEntity.ok(DeliveryAssignedResponse.builder()
                .driverId(UUID.randomUUID().toString())
                .orderId(orderId)
                .build());
    }
}
