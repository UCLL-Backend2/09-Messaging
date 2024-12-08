package be.ucll.backend2.messaging.rabbitmq_chatroom.controller;

import be.ucll.backend2.messaging.rabbitmq_chatroom.model.Message;
import be.ucll.backend2.messaging.rabbitmq_chatroom.service.MessageService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Message>> streamMessages() {
        return messageService.subscribe()
                .map(message ->
                    ServerSentEvent.<Message>builder()
                            .id(String.valueOf(message.getId()))
                            .data(message)
                            .build()
                );
    }

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

}
