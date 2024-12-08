const messagesDiv = document.getElementById('messages');
const messageInput = document.getElementById('message');
const form = document.getElementById('compose-message');

function addMessage(message, prepend = false) {
    const messageId = `msg-${message.id}`;
    if (document.getElementById(messageId) !== null) {
        return;
    }
    const div = document.createElement("div");
    const p = document.createElement("p");
    p.innerText = message.message;
    div.append(p);
    if (prepend) {
        messagesDiv.prepend(div);
    } else {
        messagesDiv.append(div);
    }
}

form.addEventListener('submit', async (event) => {
    event.preventDefault();
    const message = {
        message: messageInput.value,
    };
    messageInput.value = '';
    await fetch('/api/v1/message', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(message),
    });
});

const eventSource = new EventSource('/api/v1/message/stream');
eventSource.onmessage = (event) => {
    const message = JSON.parse(event.data);
    addMessage(message);
};

const response = await fetch("/api/v1/message");
const messages = await response.json();
messages.reverse().forEach((message) => {
    addMessage(message, true);
});
