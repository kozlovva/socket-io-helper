package io.github.kozlovva.websocket.core.messaging;

public interface WebsocketMessagingService {

    void send(String connectionSessionId, String eventName, Object message);

}
