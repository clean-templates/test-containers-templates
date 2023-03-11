package com.order.service.core.application;

import com.order.service.api.ApprovedOrderApiResponse;

public interface IOrderQueryHandler {
    ApprovedOrderApiResponse approveOrder(String orderId);
}
