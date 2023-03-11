package com.notifcaiton.service.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class QueueBindingConfig {

    private final QueueConfigHelper queueConfigHelper;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.queueConfigHelper.getExchange());
    }

    @Bean
    public Queue testQueue() {
        return new Queue(this.queueConfigHelper.getTestQueue());
    }

    @Bean
    public Binding internalToNotificationBinding() {
        return BindingBuilder
                .bind(testQueue())
                .to(internalTopicExchange())
                .with(this.queueConfigHelper.getTestRoutingKey());
    }


}