package com.test.containers.templates.containers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.containers.templates.containers.infra.ConsulTestContainer;
import com.test.containers.templates.containers.infra.PostgresTestContainer;
import com.test.containers.templates.containers.infra.RabbitMqTestContainer;
import com.test.containers.templates.containers.services.DeliveryServiceContainer;
import com.test.containers.templates.containers.services.NotificationServiceContainer;
import com.test.containers.templates.containers.services.OrderServiceContainer;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.databind.ObjectReader;

@SpringBootTest
public class AcceptanceTestLauncher {

    public static final String ORDER_DOES_NOT_EXIST = "ORDER";
    private final ObjectReader objectReader = new ObjectMapper().reader();

    @BeforeAll
    static void beforeAll() {
        PostgresTestContainer postgresContainer = PostgresTestContainer.getInstance();
        postgresContainer.start();

        ConsulTestContainer consulContainer = ConsulTestContainer.getInstance();
        consulContainer.start();

        RabbitMqTestContainer rabbitMqTestContainer = RabbitMqTestContainer.getInstance();
        rabbitMqTestContainer.start();

        OrderServiceContainer orderServiceContainer = OrderServiceContainer.getInstance();
        orderServiceContainer.start();

        DeliveryServiceContainer deliveryServiceContainer = DeliveryServiceContainer.getInstance();
        deliveryServiceContainer.start();

        NotificationServiceContainer notificationServiceContainer = NotificationServiceContainer.getInstance();
        notificationServiceContainer.start();

        baseURI = "http://localhost:" + orderServiceContainer.getMappedPort(8080);
    }

    @Test
    @DisplayName("should return order not found when approving an order that does not exist")
    void should_return_order_not_found_when_approving_an_order_that_does_not_exist() throws IOException {
        Response response = given()
                .when()
                .post("/orders/{orderId}", ORDER_DOES_NOT_EXIST)
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .extract()
                .response();
        
        Assertions.assertEquals("Order With Id: " + ORDER_DOES_NOT_EXIST + " is not found", response.getBody().print());
    }
    
    @Test
    @DisplayName("should return an approved order response when order is approved")
    void should_return_an_approved_order_response_when_order_is_approved() throws IOException {
        Response response = given()
                .when()
                .post("/orders/{orderId}", "ORD#1")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        ApprovedOrderApiResponse actualResponse = objectReader.readValue(response.getBody().print(), ApprovedOrderApiResponse.class);
        Assertions.assertEquals("ORD#1", actualResponse.getOrderId());
    }
}
