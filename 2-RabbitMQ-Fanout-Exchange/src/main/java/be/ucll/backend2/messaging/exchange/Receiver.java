package be.ucll.backend2.messaging.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("receiver")
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    // This listener creates a new auto-delete queue (it is created on program startup,
    // deleted when the program ends) and binds it to an exchange.
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = RabbitConfig.MESSAGE_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
            )
    )
    public void receiveMessage(Message message) {
        log.info("Received message: {}", message);
    }

}
