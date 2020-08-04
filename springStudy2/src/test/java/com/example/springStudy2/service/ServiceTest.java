package com.example.springStudy2.service;

import com.example.springStudy2.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServiceTest {
    Service service = new Service();

    @Test
    void shouldThrowException(){
        Member member1 = new Member();
        member1.setName("Kim");
        service.join(member1);

        Member member2 = new Member();
        member2.setName("Youn");
        Assertions.assertThrows(IllegalStateException.class,()-> service.join(member2));
        // service.join(member2)를 넣었을 때 예외와 IllegalStateException의 예외가 같은지 확인하는 코드

        //예외 처리
    }
}
