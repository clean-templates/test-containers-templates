package com.order.service.core.application;

import com.order.service.infra.external.services.DeliveryAssignedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeliveryClient {
    ResponseEntity<DeliveryAssignedResponse> assignOrder(@PathVariable String orderId);
}
