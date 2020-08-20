package com.example.websocket.message;

public class Greeting { // 내보낼때 사용하는 클래스
    private String content;

    public Greeting(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

}
