package com.order.service.runner;

import com.order.service.infra.IOrderProvider;
import com.order.service.infra.OrderJpaRepository;
import com.order.service.infra.model.OrderModel;
import com.order.service.infra.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultMigrationRunner implements CommandLineRunner {

    /***
     * This is for a demo purpose
     *  you should never have a runner that access directly your repository
     *  you should use something similar to liquidbase or flyway for migration
     *  but for this demo we will be focusing on test containers rather than this
     */

    private OrderJpaRepository orderJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Checking for initial data");
        List<OrderModel> all = orderJpaRepository.findAll();
        if (all.size() == 0) {
            log.info("no data found, adding migration data");
            orderJpaRepository.saveAll(List.of(
                    OrderModel.builder().orderId("ORD#1").orderStatus(OrderStatus.ON_GOING).build(),
                    OrderModel.builder().orderId("ORD#2").orderStatus(OrderStatus.APPROVED).build()
                    ));
            log.info("migration added");
            return;
        }
        log.info("data migration already exist, no data will be inserted");
    }
}
