package com.test.containers.templates.containers.infra;

import org.testcontainers.containers.Network;

public class NetworkFactory {

    private static Network network;

    private NetworkFactory() {

    }
    public static Network getSharedNetworkInstance() {
        if (network == null) {
            network = Network.newNetwork();
        }
        return network;
    }
}
