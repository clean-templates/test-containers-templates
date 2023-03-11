package com.order.service.infra;

import com.order.service.infra.model.OrderModel;

public interface IOrderProvider {

    OrderModel getOrder(String orderId);

    OrderModel save(OrderModel order);
}
