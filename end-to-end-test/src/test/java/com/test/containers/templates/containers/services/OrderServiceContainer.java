package com.test.containers.templates.containers.services;

import com.test.containers.templates.containers.infra.NetworkFactory;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OrderServiceContainer extends GenericContainer<OrderServiceContainer> {

    public static final String SERVICE_NAME = "order-service";
    public static final String SERVICE_PROFILE = "acceptance";
    private static OrderServiceContainer container;

    private OrderServiceContainer() {
        super((new ImageFromDockerfile()).withDockerfile(getDockerPath(SERVICE_NAME)));
        this.withExposedPorts(8080);
        this.withNetworkAliases(SERVICE_NAME);
        this.withEnv("SPRING_PROFILES_ACTIVE", SERVICE_PROFILE);
        this.withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(SERVICE_NAME)));
        this.withNetwork(NetworkFactory.getSharedNetworkInstance());
    }

    public static OrderServiceContainer getInstance() {
        if (container == null) {
            container = new OrderServiceContainer();
        }
        return container;
    }


    private static Path getDockerPath(String serviceNamePath) {
        return Paths.get((new File("")).getAbsolutePath(), "..", serviceNamePath, "Dockerfile");
    }

    @Override
    public void start() {
        super.start();
    }
}
