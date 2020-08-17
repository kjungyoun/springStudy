package kr.ac.inu.mqdemo.receiver;

import org.springframework.amqp.core.Message;

public class MessageReceiver {
    public void receive(byte[] message) {          //메세지는 기본적으로 byte로 날아온다.
        System.out.println(new String(message)); //byte형의 메세지를 String형으로 바꿔서 출력한다.
    }
    public void receive(String message){
            System.out.println(message);
        }
        public void receive(Message message){
            System.out.println(message.toString());
        }


}
