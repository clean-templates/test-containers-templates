package com.test.containers.templates.containers;

import com.test.containers.templates.containers.infra.ConsulTestContainer;
import com.test.containers.templates.containers.infra.PostgresTestContainer;
import com.test.containers.templates.containers.services.OrderServiceContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.Network;

@SpringBootTest
public class AcceptanceTestLauncher {

    @BeforeAll
    static void beforeAll() {

        PostgresTestContainer postgresContainer = PostgresTestContainer.getInstance();
        postgresContainer.start();

        ConsulTestContainer consulContainer = ConsulTestContainer.getInstance();
        consulContainer.start();

        OrderServiceContainer container = new OrderServiceContainer();
        container.start();
    }
    
    @Test
    @DisplayName("test")
    void test() {

    }
}
