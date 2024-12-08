# RabbitMQ Chatroom

Dit voorbeeld combineert het fanout exchange RabbitMQ voorbeeld met het SSE chatroom voorbeeld.

Je kan nu meerde instanties opstarten van dezelfde applicatie (op een andere poort). Zolang
deze verbonden zijn met dezelfde RabbitMQ instantie en PostgreSQL database zal je berichten
kunnen sturen tussen deze verschillende instanties.

Op http://localhost:8080 (of een ander poortnummer) zal je een simpele client implementatie vinden ter demonstratie.