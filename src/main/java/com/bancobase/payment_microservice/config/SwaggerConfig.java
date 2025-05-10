package com.bancobase.payment_microservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "API de Pagos", version = "1.0",
        description = "Microservicio de Pagos donde puedes guardar el pago que un cliente realiza a otro, también puede " +
                "obtener el estatus de los pagos realizados, así como actualizar el estatus de los pagos. Al actualizar el estatus" +
                "de un pago se envia una notificación en RabbitMQ.",
        contact = @Contact(
                name = "Ing. Eulices Martinez Santamaria",
                email = "eulices.santamaria@gmail.com"
        )),
        servers = {
                @Server(
                        description = "API DEV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "RabbitMQ DEV",
                        url = "http://localhost:15672/"
                )
        }
)
@Configuration
public class SwaggerConfig {
}
