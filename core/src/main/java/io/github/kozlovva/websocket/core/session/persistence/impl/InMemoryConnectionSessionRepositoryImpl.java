package io.github.kozlovva.websocket.core.session.persistence.impl;

import com.corundumstudio.socketio.SocketIOClient;
import io.github.kozlovva.websocket.core.session.ConnectionSession;
import io.github.kozlovva.websocket.core.session.persistence.ConnectionSessionRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryConnectionSessionRepositoryImpl implements ConnectionSessionRepository {

    private final Map<String, ConnectionSession> storage = new HashMap<>();

    @Override
    public void save(ConnectionSession connectionSession) {
        storage.put(connectionSession.getId(), connectionSession);
    }

    @Override
    public void remove(SocketIOClient session) {
        storage.entrySet()
                .removeIf(e -> e.getValue().getClient().equals(session));
    }

    @Override
    public void remove(String id) {
        storage.entrySet()
                .removeIf(e -> e.getValue().getId().equals(id));
    }

    @Override
    public ConnectionSession findById(String id) {
        return storage.values()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Session not found by ID"));
    }

    @Override
    public ConnectionSession findByClient(SocketIOClient client) {
        return storage.values()
                .stream()
                .filter(s -> s.getClient().getSessionId().equals(client.getSessionId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Session not found by client"));
    }
}
