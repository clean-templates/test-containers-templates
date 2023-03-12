package com.order.service.infra;

import com.order.service.configs.FeignClientConfig;
import com.order.service.infra.model.OrderModel;
import com.order.service.infra.model.OrderStatus;
import containers.postgres.PostgresTestContainer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.awt.desktop.OpenFilesEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest()
@ActiveProfiles("test")
public class OrderProviderIntegrationTest {

    public static final String NOT_FOUND = "NOT_FOUND";
    @Autowired
    private OrderJpaRepository orderJpaRepository;


    private OrderProvider orderProvider;

    @BeforeAll
    static void beforeAll() {
        PostgresTestContainer instance = PostgresTestContainer.getInstance();
        instance.start();
    }

    @BeforeEach
    void setUp() {
        orderProvider = new OrderProvider(orderJpaRepository);
    }

    @Test
    @DisplayName("get order by id will return order model if found")
    void get_order_by_id_will_return_order_model_if_found() {
        OrderModel order = orderProvider.getOrder("ORD#1");
        assertEquals(getExpectedOrder(), order);
    }


   @Test
   @DisplayName("get by id will return order not found if order does not exist")
   void get_by_id_will_return_order_not_found_if_order_does_not_exist() {
       OrderNotFoundException orderNotFoundException = assertThrows(OrderNotFoundException.class, () -> orderProvider.getOrder(NOT_FOUND));
       assertEquals(getMessage(), orderNotFoundException.getMessage());
   }

    private String getMessage() {
        return "Order With Id: NOT_FOUND is not found";
    }

    private OrderModel getExpectedOrder() {
        return OrderModel.builder().orderId("ORD#1").orderStatus(OrderStatus.ON_GOING).build();
    }
}