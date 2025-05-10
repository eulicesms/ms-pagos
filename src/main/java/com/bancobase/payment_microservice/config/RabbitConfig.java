package com.bancobase.payment_microservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${pagos.queue.name}")
    private String cola;

    @Bean
    public Queue queue() {
        return new Queue(cola, true);
    }
}
