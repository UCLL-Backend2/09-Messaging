package be.ucll.backend2.messaging.rabbitmq_chatroom.service;

import be.ucll.backend2.messaging.rabbitmq_chatroom.config.RabbitConfig;
import be.ucll.backend2.messaging.rabbitmq_chatroom.model.Message;
import be.ucll.backend2.messaging.rabbitmq_chatroom.repository.MessageRepository;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class MessageService {

    // Create a "sink" for in-process publish/subscribe communication
    private final Sinks.Many<Message> sink = Sinks.many().multicast().onBackpressureBuffer();

    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;

    public MessageService(MessageRepository messageRepository, RabbitTemplate rabbitTemplate) {
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Flux<Message> subscribe() {
        return sink.asFlux();
    }

    public Message sendMessage(Message message) {
        message = messageRepository.save(message);
        rabbitTemplate.convertAndSend(
                RabbitConfig.MESSAGE_EXCHANGE_NAME,
                "",
                message
        );
        return message;
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = RabbitConfig.MESSAGE_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
            )
    )
    public void receiveMessage(Message message) {
        sink.tryEmitNext(message);
    }

}
