package kr.ac.inu.mqdemo;

import kr.ac.inu.mqdemo.receiver.MessageReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    static final String QUEUE_NAME = "Kim"; // 임의로 정해도 된다
    static final String EXCHANGE_NAME ="Kim"; // 임의로 정해도 된다
    static final String ROUNTING_KEY = "foo.bar.#";
    @Bean
    Queue queue(){
        return new Queue(QUEUE_NAME,true);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUNTING_KEY);
    }

    // 밑에 세개의 객체들은 어떻게 수신하여 처리할 것인가를 의미

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter adapter){ // application.properties에 있는 rabbitMQ 서버에 연결하는 것
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(factory);  //서버 ip, port, 이름, 패스워드
//        container.setQueueNames(QUEUE_NAME);     //처리할 큐들 목록 관리(구독)
//        container.setMessageListener(adapter);  // 어떻게 처리할지
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 이렇게 하면 메세지를 다 읽어도 자동으로 사라지지 않음, 따라서 수동으로 지워줘야함

        return container;
    }

//    @Bean
//    MessageListenerAdapter adapter(MessageReceiver receiver){ // 어댑터 패턴을 이용
//        return new MessageListenerAdapter(receiver,"receive"); //클래스와 클래스 안의 "메소드명"이 들어간다.
//        // 메세지를 받으면 receiver 객체 안에 있는 receive 메소드를 이용해 처리하겠다.
//    }

    @Bean
    MessageReceiver receiver(){
        return new MessageReceiver();
    }
}
