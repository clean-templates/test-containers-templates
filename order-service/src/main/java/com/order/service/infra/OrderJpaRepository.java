package com.order.service.infra;

import com.order.service.infra.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderModel, String> {
}
