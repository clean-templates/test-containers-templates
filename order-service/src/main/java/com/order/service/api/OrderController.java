package com.order.service.api;

import com.order.service.core.application.IOrderQueryHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private IOrderQueryHandler orderHandler;

    @PostMapping("/{orderId}")
    public ResponseEntity<ApprovedOrderApiResponse> approveOrder(@PathVariable String orderId){
        ApprovedOrderApiResponse approvedOrderApiResponseBuilder = orderHandler.approveOrder(orderId);
        return ResponseEntity.ok(approvedOrderApiResponseBuilder);
    }
}
