package com.test.containers.templates.containers.infra;

import org.testcontainers.containers.RabbitMQContainer;

import java.util.Set;

public class RabbitMqTestContainer extends RabbitMQContainer {

    public static final String IMAGE_VERSION = "rabbitmq:3-management-alpine";
    public static final String NETWORK_ALIASES = "rabbitmq";
    private static RabbitMqTestContainer container;

    private RabbitMqTestContainer() {
        super(IMAGE_VERSION);
        this.withNetwork(NetworkFactory.getSharedNetworkInstance());
        this.withNetworkAliases(NETWORK_ALIASES);
        this.withExposedPorts(15672, 5672);
        this.withUser("guest", "guest", Set.of("administrator"));
    }

    public static RabbitMqTestContainer getInstance() {
        if (container == null) {
            container = (RabbitMqTestContainer) new RabbitMqTestContainer().withAccessToHost(true);
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
    }

}