package be.ucll.backend2.messaging.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("receiver")
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = RabbitConfig.MESSAGE_QUEUE_NAME)
    public void receiveMessage(Message message) {
        log.info("Received message: {}", message);
    }

}
