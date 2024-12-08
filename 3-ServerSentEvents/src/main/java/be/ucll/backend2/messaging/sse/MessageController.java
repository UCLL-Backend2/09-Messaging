package be.ucll.backend2.messaging.sse;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    @GetMapping("/stream")
    public Flux<ServerSentEvent<Message>> streamMessages() {
        return Flux.interval(Duration.ofSeconds(1L))
                .map(l -> {
                    final var message = new Message("Message " + l);
                    return ServerSentEvent.<Message>builder()
                            .id(String.valueOf(l))
                            .data(message)
                            .build();
                });
    }
}
