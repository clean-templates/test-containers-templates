package com.order.service.core.application;

public interface IMessagePublisher {
    void publish(Object payload, String exchange, String routingKey);
}