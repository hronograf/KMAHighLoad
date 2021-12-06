package com.common;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Declarables topicBindings() {
        Queue createBookQueue = new Queue(RabbitMqSettings.createQueue, false);
        Queue deleteBookQueue = new Queue(RabbitMqSettings.deleteQueue, false);

        TopicExchange topicExchange = new TopicExchange(RabbitMqSettings.exchangeName);

        return new Declarables(
                topicExchange,
                createBookQueue,
                deleteBookQueue,
                BindingBuilder
                        .bind(createBookQueue)
                        .to(topicExchange).with(RabbitMqSettings.createBookRoutingKey),
                BindingBuilder
                        .bind(deleteBookQueue)
                        .to(topicExchange).with(RabbitMqSettings.deleteBookRoutingKey)
        );
    }
}
