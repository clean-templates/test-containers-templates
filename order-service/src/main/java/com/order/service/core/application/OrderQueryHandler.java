package com.order.service.core.application;

import com.order.service.api.ApprovedOrderApiResponse;
import com.order.service.infra.IOrderProvider;
import com.order.service.infra.external.services.DeliveryAssignedResponse;
import com.order.service.infra.external.services.DeliveryFeignClient;
import com.order.service.infra.model.OrderModel;
import com.order.service.infra.model.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderQueryHandler implements IOrderQueryHandler {

    public static final String EXCHANGE = "notification.exchange";
    public static final String ROUTING_KEY = "internal.notification.notification-key";
    /***
     * Again, this is for illustration purposes, you should not have a leakage from your infra to your domain
     * meaning that your OrderModel should be mapped to an Order Domain Object in your core
     */
    private IOrderProvider orderProvider;
    private DeliveryFeignClient deliveryFeignClient;
    private IMessagePublisher messagePublisher;

    @Override
    public ApprovedOrderApiResponse approveOrder(String orderId) {
        OrderModel order = orderProvider.getOrder(orderId);

        // should have better error handling in a real use case
        if (order.getOrderStatus() != OrderStatus.ON_GOING) {
            throw new IllegalStateException("Order " + orderId + " is not in invalid state!");
        }

        // should not be mutated in a real use case
        order.setOrderStatus(OrderStatus.APPROVED);
        OrderModel updatedOrder = orderProvider.save(order);

        // should be abstracted, maybe a facade in a real use case
        DeliveryAssignedResponse deliveryResponse = deliveryFeignClient.assignOrder(orderId).getBody();
        ApprovedOrderApiResponse response = ApprovedOrderApiResponse.builder().orderId(updatedOrder.getOrderId())
                .driverId(deliveryResponse.getDriverId())
                .timestamp(Timestamp.from(Instant.now())).build();

        // Routing keys should be abstracted in a real use case
        messagePublisher.publish(response, EXCHANGE, ROUTING_KEY);

        return response;

    }
}
