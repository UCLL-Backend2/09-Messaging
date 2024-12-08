# RabbitMQ exchange

Dit voorbeeld demonstreert hoe meerdere processen via de RabbitMQ berichten
kunnen uitwisselen met behulp van een exchange.

Start de applicatie met profile "sender" om een sender te starten. Start het
met profile "receiver" om een receiver te starten.

Probeer meerdere instanties van receiver tegelijk te runnen. Je zal merken dat
in tegenstelling tot bij een queue elke receiver elke message ontvangt.

Je zal ook merken dat enkel de messages sinds het opstarten van de receiver ontvangen
worden. Messages worden enkel gestuurd naar receiver die op dat moment aan het
"luisteren" zijn.

- [Message.java](src/main/java/be/ucll/backend2/messaging/queue/Message.java): een
  record met het bericht dat gestuurd wordt.
- [Sender.java](src/main/java/be/ucll/backend2/messaging/queue/Sender.java): de sender: deze stuurt in een oneindige lus messages naar de exchange met behulp van `RabbitMQTemplate`. zijn.
- [Receiver.java](src/main/java/be/ucll/backend2/messaging/queue/Receiver.java): de receiver: deze ontvangt messages met behulp van `@RabbitListener`. Deze maakt een auto-delete queue (`@Queue`) en verbindt die met de exchange.
- [RabbitConfig.java](src/main/java/be/ucll/backend2/messaging/queue/RabbitConfig.java): de configuratie: definieert de exchange en registreert de `Jackson2JsonMessageConverter` bean, zodat berichten die via RabbitMQ gestuurd worden als JSON worden verstuurd en ontvangen.
- [Application.java](src/main/java/be/ucll/backend2/messaging/queue/Application.java): een heel standaard Spring Boot main-klasse.
- [compose.yaml](compose.yaml): bevat de Docker Compose configuratie om een RabbitMQ broker op te starten.
- [application.yaml](src/main/resources/application.yaml): stelt in hoe de applicatie moet verbinden met RabbitMQ.
