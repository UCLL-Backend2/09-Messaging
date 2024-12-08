package be.ucll.backend2.messaging.rabbitmq_chatroom.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String MESSAGE_EXCHANGE_NAME = "messages";

    // Declare the RabbitMQ exchange. This ensures the exchange is created
    // if it does not exist yet
    @Bean
    public Exchange exchange() {
        return new FanoutExchange(MESSAGE_EXCHANGE_NAME);
    }

    // Register Jackson2JsonMessageConverter bean so that our messages are converted
    // to/from JSON using Jackson
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
