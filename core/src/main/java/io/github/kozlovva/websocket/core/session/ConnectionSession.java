package io.github.kozlovva.websocket.core.session;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConnectionSession {
    private String id;
    private SocketIOClient client;
}
