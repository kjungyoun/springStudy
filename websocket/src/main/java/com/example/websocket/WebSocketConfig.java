package com.example.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 이 애너테이션이 있어야 websocket이 돌아간다.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //ctrl + o 를 하면 WebSocketMessageBrokerConfigurer이 가지고있는 메소드를 구현할 수 있다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app"); // 주소 앞에 접두사를 정의 즉, app/hello, app/topic/greeting 이런 식으로 됨
    }

    // stomp (simple text oriented message protocol)

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("gs-guide-websocket").withSockJS(); //websocket을 사용할 수 없을때 withSockJS를 이용해서 대체할 수 있도록 하는 것
    }
} // git 연동 테스트입니다.
