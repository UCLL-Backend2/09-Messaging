# Server Sent Events

Dit voorbeeld toont hoe responses gestreamd kunnen worden naar de client.

[MessageController.java](src/main/java/be/ucll/backend2/messaging/sse/MessageController.java)
maakt voor de `GET /api/v1/message/stream` endpoint een `Flux` aan die
`ServerSentEvent`s stuurt die telkens een
[Message](src/main/java/be/ucll/backend2/messaging/sse/Message.java)
bevatten.

Dit doen we elke seconde met een `Flux.interval`.