package io.github.kozlovva.socketio.starter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "socketio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocketIOHelperProperties {
    private String host = "0.0.0.0";
    private int maxFramePayloadLength = 1048576;
    private int maxHttpContentLength = 1048576;
    private Integer port = 9090;
    private int bossCount = 1;
    private int workCount = 10;
    private boolean allowCustomRequests = true;
    private int upgradeTimeout = 1000000;
    private int pingTimeout = 6000000;
    private int pingInterval = 25000;
}
