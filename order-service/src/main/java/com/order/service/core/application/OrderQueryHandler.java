package com.order.service.core.application;

import com.order.service.api.ApprovedOrderApiResponse;
import com.order.service.infra.IOrderProvider;
import com.order.service.infra.model.OrderModel;
import com.order.service.infra.model.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@AllArgsConstructor
public class OrderQueryHandler implements IOrderQueryHandler {

    /***
     * Again, this is for illustration purposes, you should not have a leakage from your infra to your domain
     * meaning that your OrderModel should be mapped to an Order Domain Object in your core
     */
    private IOrderProvider orderProvider;

    @Override
    public ApprovedOrderApiResponse approveOrder(String orderId) {
        OrderModel order = orderProvider.getOrder(orderId);

        if(order.getOrderStatus() != OrderStatus.ON_GOING){
            throw new IllegalStateException("Order " + orderId +  " is not in invalid state!");
        }
        order.setOrderStatus(OrderStatus.APPROVED);
        OrderModel updatedOrder = orderProvider.save(order);

        return ApprovedOrderApiResponse.builder().orderId(updatedOrder.getOrderId()).timestamp(Timestamp.from(Instant.now())).build();

    }
}
