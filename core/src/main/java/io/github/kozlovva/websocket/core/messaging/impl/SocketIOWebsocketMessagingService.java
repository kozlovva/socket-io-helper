package io.github.kozlovva.websocket.core.messaging.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kozlovva.websocket.core.messaging.WebsocketMessagingService;
import io.github.kozlovva.websocket.core.session.persistence.ConnectionSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class SocketIOWebsocketMessagingService implements WebsocketMessagingService {

    private final ConnectionSessionRepository connectionSessionRepository;
    private final ObjectMapper mapper;

    @Override
    public void send(String connectionSessionId, String eventName, Object message) {
        var session = connectionSessionRepository.findById(connectionSessionId);
        try {
            var data = mapper.writeValueAsString(message);
            session.getClient().sendEvent(eventName, data);
            log.debug("Message " + data.toString() + " send to connection " + connectionSessionId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
