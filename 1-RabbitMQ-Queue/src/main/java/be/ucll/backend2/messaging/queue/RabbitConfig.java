package be.ucll.backend2.messaging.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String MESSAGE_QUEUE_NAME = "messages";

    // Declare the RabbitMQ queue. This ensures the queue is created
    // if it does not exist yet
    @Bean
    public Queue queue() {
        return new Queue(MESSAGE_QUEUE_NAME);
    }

    // Register Jackson2JsonMessageConverter bean so that our messages are converted
    // to/from JSON using Jackson
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
