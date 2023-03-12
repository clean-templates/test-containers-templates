package com.test.containers.templates.containers.infra;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresTestContainer extends PostgreSQLContainer<PostgresTestContainer> {

    public static final String IMAGE_VERSION = "postgres:13.5";
    private static final String POSTGRES_USERNAME = "user";
    private static final String POSTGRES_PASSWORD = "pass213";
    private static final String NETWORK_ALIASES = "postgres-test-container";
    private static final String DATABASE_NAME = "test";

    private static PostgresTestContainer container;

    private PostgresTestContainer() {
        super(IMAGE_VERSION);
        this.withExposedPorts(5432);
        this.withUsername(POSTGRES_USERNAME);
        this.withPassword(POSTGRES_PASSWORD);
        this.withNetworkAliases(NETWORK_ALIASES);
        this.withNetwork(NetworkFactory.getSharedNetworkInstance());

    }

    public static PostgresTestContainer getInstance() {
        if (container == null) {
            container = new PostgresTestContainer().withDatabaseName(DATABASE_NAME);
        }
        return container;
    }

    @Override
    public void start() {
        super.start();

    }

    @Override
    public void stop() {
        super.stop();
    }
}
