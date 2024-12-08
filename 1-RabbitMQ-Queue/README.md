# RabbitMQ queue

Dit voorbeeld demonstreert hoe meerdere processen via de RabbitMQ berichten
kunnen uitwisselen met behulp van een queue.

Start de applicatie met profile "sender" om een sender te starten. Start het
met profile "receiver" om een receiver te starten.

Probeer meerdere instanties van receiver tegelijk te runnen. Je zal merken dat
telkens slechts één receiver een message ontvangt. Bij een gewone queue zal dus
elke message slechts bij één ontvanger aankomen.

- [Message.java](src/main/java/be/ucll/backend2/messaging/queue/Message.java): een
  record met het bericht dat gestuurd wordt.
- [Sender.java](src/main/java/be/ucll/backend2/messaging/queue/Sender.java): de sender: deze stuurt in een oneindige lus messages naar de queue met behulp van `RabbitMQTemplate`.
- [Receiver.java](src/main/java/be/ucll/backend2/messaging/queue/Receiver.java): de receiver: deze ontvangt messages van de queue met behulp van `@RabbitListener`.
- [RabbitConfig.java](src/main/java/be/ucll/backend2/messaging/queue/RabbitConfig.java): de configuratie: definieert de queue en registreert de `Jackson2JsonMessageConverter` bean, zodat berichten die via RabbitMQ gestuurd worden als JSON worden verstuurd en ontvangen.
- [Application.java](src/main/java/be/ucll/backend2/messaging/queue/Application.java): een heel standaard Spring Boot main-klasse.
- [compose.yaml](compose.yaml): bevat de Docker Compose configuratie om een RabbitMQ broker op te starten.
- [application.yaml](src/main/resources/application.yaml): stelt in hoe de applicatie moet verbinden met RabbitMQ.