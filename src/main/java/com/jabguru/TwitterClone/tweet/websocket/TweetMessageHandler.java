package com.jabguru.TwitterClone.tweet.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class TweetMessageHandler implements WebSocketHandler {
    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions;

    TweetMessageHandler() {
        sessions = Collections.synchronizedSet(new HashSet<>());
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("connection established");
        sessions.add(session);
        session.sendMessage(new TextMessage("connected"));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("message arrived {}", message);
    }

    public void sendMessage(TweetMessage message) throws IOException {
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    String messageString = objectMapper.writeValueAsString(message);
                    session.sendMessage(new TextMessage(messageString));
                }
            }
        }

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("transport error");

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        sessions.remove(session);
        log.info("connection closed");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}