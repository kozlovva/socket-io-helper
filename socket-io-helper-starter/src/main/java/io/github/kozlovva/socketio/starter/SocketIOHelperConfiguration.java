package io.github.kozlovva.socketio.starter;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kozlovva.websocket.core.messaging.WebsocketMessagingService;
import io.github.kozlovva.websocket.core.messaging.impl.SocketIOWebsocketMessagingService;
import io.github.kozlovva.websocket.core.session.persistence.ConnectionSessionRepository;
import io.github.kozlovva.websocket.core.session.persistence.impl.InMemoryConnectionSessionRepositoryImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfigureAfter({JacksonAutoConfiguration.class})
@EnableConfigurationProperties(SocketIOHelperProperties.class)
@Configuration
public class SocketIOHelperConfiguration {

    @Bean
    public SocketIOServer socketIOServer(SocketIOHelperProperties properties) {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setMaxFramePayloadLength(properties.getMaxFramePayloadLength());
        config.setMaxHttpContentLength(properties.getMaxHttpContentLength());
        config.setHostname(properties.getHost());
        config.setPort(properties.getPort());
        config.setBossThreads(properties.getBossCount());
        config.setWorkerThreads(properties.getWorkCount());
        config.setAllowCustomRequests(properties.isAllowCustomRequests());
        config.setUpgradeTimeout(properties.getUpgradeTimeout());
        config.setPingTimeout(properties.getPingTimeout());
        config.setPingInterval(properties.getPingInterval());
        config.setTransports(Transport.WEBSOCKET, Transport.POLLING);
        var server =  new SocketIOServer(config);
        server.addNamespace("");
        return server;
    }

    @ConditionalOnMissingBean
    @Bean
    public ConnectionSessionRepository connectionSessionRepository() {
        return new InMemoryConnectionSessionRepositoryImpl();
    }

    @ConditionalOnMissingBean
    @Bean
    public WebsocketMessagingService websocketMessagingService(ConnectionSessionRepository connectionSessionRepository, ObjectMapper objectMapper) {
        return new SocketIOWebsocketMessagingService(connectionSessionRepository, objectMapper);
    }

}
