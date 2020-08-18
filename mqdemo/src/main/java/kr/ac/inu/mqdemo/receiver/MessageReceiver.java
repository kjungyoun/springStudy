package kr.ac.inu.mqdemo.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "inu") // 리스너라고 명시해주는 애너테이션(큐는 springConfig에서 입력한 큐의 이름과 동일해야함)
public class MessageReceiver {
    @RabbitListener
    public void receive(byte[] message) {          //메세지는 기본적으로 byte로 날아온다.
        System.out.println(new String(message)); //byte형의 메세지를 String형으로 바꿔서 출력한다.
    }
    @RabbitListener
    public void receive(String message){
            System.out.println(message);
        }
        public void receive(Message message){
            System.out.println(message.toString());
        }


}
