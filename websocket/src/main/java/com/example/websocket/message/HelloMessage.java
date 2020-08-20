package com.example.websocket.message;

public class HelloMessage { //publicher가 보내는 메세지의 타입을 정의
    private String name;

    public HelloMessage() {
    } // bean 생성자

    public HelloMessage(String name){this.name=name;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
