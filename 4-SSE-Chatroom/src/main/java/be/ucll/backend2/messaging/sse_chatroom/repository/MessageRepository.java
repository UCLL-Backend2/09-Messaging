package be.ucll.backend2.messaging.sse_chatroom.repository;

import be.ucll.backend2.messaging.sse_chatroom.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
