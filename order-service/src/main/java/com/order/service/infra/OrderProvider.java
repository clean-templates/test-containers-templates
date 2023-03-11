package com.order.service.infra;

import com.order.service.infra.model.OrderModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrderProvider implements IOrderProvider {

    private OrderJpaRepository orderJpaRepository;

    @Override
    public OrderModel getOrder(String orderId) {
        return orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order With Id: " + orderId + " is not found"));
    }

    @Override
    public OrderModel save(OrderModel order) {
        return orderJpaRepository.save(order);
    }


}
