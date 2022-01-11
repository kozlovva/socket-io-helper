package io.github.kozlovva.websocket.core.session.persistence;

import com.corundumstudio.socketio.SocketIOClient;
import io.github.kozlovva.websocket.core.session.ConnectionSession;

import java.util.Optional;

public interface ConnectionSessionRepository {
    void save(ConnectionSession connectionSession);

    void remove(SocketIOClient session);

    void remove(String id);

    ConnectionSession findById(String id);

    ConnectionSession findByClient(SocketIOClient client);
}
