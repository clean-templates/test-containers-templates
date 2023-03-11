package com.notifcaiton.service.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class QueueConfigHelper {

    @Value("${rabbitmq.exchanges.internal}")
    private String exchange;

    @Value("${rabbitmq.queues.test-queue}")
    private String testQueue;

    @Value("${rabbitmq.routing-keys.test-key}")
    private String testRoutingKey;



}