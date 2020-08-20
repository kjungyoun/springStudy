package com.example.websocket.controller;

import com.example.websocket.message.Greeting;
import com.example.websocket.message.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController { // 메세지 수신자가 메세지를 받아서 응답할 타입을 정의
    //@MessageMapping("/hello")
    // publisher ->[/hello] [HelloMessage] (ex {"name" : xxx} ->broker ->subscriber(@MessageMapping)
    //@SendTo("topic/greetings") subscriber가 메세지를 보내는 것, 즉 구독자가 publisher가 된다.
    // publisher ->[/topic/greeting] [Greeting] (Jackson2Json) -> broker -> subscriber(@MessageMapping)
    @MessageMapping("/hello") //publisher가 helloMessage를 보내는 것
    @SendTo("/topic/greetings") // 메세지 수신자가 메세지를 응답하는 것
    public Greeting greeting(HelloMessage message){
        return new Greeting("Hello " + HtmlUtils.htmlEscape(message.getName()));
    }
}
