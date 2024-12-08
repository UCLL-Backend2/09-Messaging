# SSE Chatroom

Dit voorbeeld toont hoe een `Flux<ServerSentEvent<...>>` en een `Sinks.Many<...>`
kunnen gebruikt worden om een eenvoudige chatroom te implementeren.

In dit voorbeeld worden nieuwe messages ontvangen met een `POST` request,
opgeslagen in de H2 database en zijn alle messages opvraagbaar met een `GET` request.

Wat hierbij speciaal is, is dat nieuwe berichten ook worden "gebroadcast" naar
elke client die "luistert" op `/api/v1/message/stream`.