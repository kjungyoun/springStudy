package com.example.springStudy2.service;

import com.example.springStudy2.domain.Member;
import com.example.springStudy2.repository.Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // 클래스 안에 있는 메소드들의 테스트를 수행하고 다 rollback 시킨다.
public class DemoServiceTest {

    @Autowired
    private Service service;
    @Autowired
    private Repository repository;

    @Test // 실제 db에 직접 넣어보지 않고 넣어보라고 시키는 것
    void join(){
        Member m = new Member();
        m.setName("포장마차");
        Long id = service.join(m);

        Member m2 = service.findOne(id).get();
        Assertions.assertThat(m.getName()).isEqualTo(m2.getName());

    }

    @Test
    void duplicate(){
        Member m1 = new Member();
        m1.setName("마테차");
        Member m2 = new Member();
        m2.setName("마테차");

        service.join(m1);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, ()->service.join(m2));
    }

}
