package be.ucll.backend2.messaging.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Profile("sender")
public class Sender implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Sender.class);
    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        long i = 0L;
        try {
            while (true) {
                final var message = new Message("Message " + i);
                log.info("Sending mesage: {}", message);
                rabbitTemplate.convertAndSend(
                        RabbitConfig.MESSAGE_QUEUE_NAME,
                        new Message("Message " + i));
                i++;
                Thread.sleep(Duration.ofSeconds(1L));
            }
        } catch (InterruptedException ignored) {
            log.info("Stopping sender...");
        }
    }

}
