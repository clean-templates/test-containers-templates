package com.order.service.infra.external.services;


import com.order.service.core.application.DeliveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "delivery-service")
public interface DeliveryFeignClient extends DeliveryClient {

    @GetMapping("/deliver/{orderId}")
    ResponseEntity<DeliveryAssignedResponse> assignOrder(@PathVariable String orderId);
}