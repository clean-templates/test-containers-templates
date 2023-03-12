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

    public OrderServiceContainer(){
        super((new ImageFromDockerfile()).withDockerfile(getDockerPath("order-service")));
        this.withExposedPorts(8080);
        this.withNetworkAliases("order-service");
        this.withEnv("SPRING_PROFILES_ACTIVE", "acceptance");
        this.withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger("order-service")));
        this.withNetwork(NetworkFactory.getSharedNetworkInstance());
    }

    private static Path getDockerPath(String serviceNamePath) {
        return Paths.get((new File("")).getAbsolutePath(), "..", serviceNamePath, "Dockerfile");
    }

    @Override
    public void start() {
        super.start();
    }
}
