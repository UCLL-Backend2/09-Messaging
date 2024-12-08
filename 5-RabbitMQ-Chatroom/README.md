# RabbitMQ Chatroom

Dit voorbeeld combineert het fanout exchange RabbitMQ voorbeeld met het SSE chatroom voorbeeld.

Je kan nu meerde instanties opstarten van dezelfde applicatie (op een andere poort). Zolang
deze verbonden zijn met dezelfde RabbitMQ instantie en PostgreSQL database zal je berichten
kunnen sturen tussen deze verschillende instanties.