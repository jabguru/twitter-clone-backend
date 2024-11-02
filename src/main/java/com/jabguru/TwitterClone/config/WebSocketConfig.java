package com.jabguru.TwitterClone.config;

import com.jabguru.TwitterClone.notification.websocket.NotificationMessageHandler;
import com.jabguru.TwitterClone.user.websocket.UserMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.jabguru.TwitterClone.tweet.websocket.TweetMessageHandler;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final TweetMessageHandler tweetMessageHandler;
    private final UserMessageHandler userMessageHandler;
    private final NotificationMessageHandler notificationMessageHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(tweetMessageHandler, "/tweets");
        registry.addHandler(userMessageHandler, "/user");
        registry.addHandler(notificationMessageHandler, "/notifications");
    }
}
