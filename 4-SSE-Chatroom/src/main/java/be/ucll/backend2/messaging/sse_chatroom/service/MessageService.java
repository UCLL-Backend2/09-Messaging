package be.ucll.backend2.messaging.sse_chatroom.service;

import be.ucll.backend2.messaging.sse_chatroom.model.Message;
import be.ucll.backend2.messaging.sse_chatroom.repository.MessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class MessageService {

    // Create a "sink" for in-process publish/subscribe communication
    private final Sinks.Many<Message> sink = Sinks.many().multicast().onBackpressureBuffer();

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Flux<Message> subscribe() {
        return sink.asFlux();
    }

    public Message sendMessage(Message message) {
        message = messageRepository.save(message);
        sink.tryEmitNext(message);
        return message;
    }

}
