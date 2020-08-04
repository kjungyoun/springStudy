package com.example.springStudy2.repository;

import com.example.springStudy2.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryRepositoryTest { //내가 설계한 class를 test하기 위한 코드 (코드로 코드를 검증하는 방식)
    MemoryRepository rep = new MemoryRepository();

    @AfterEach //클래스 전체를 테스트시 save먼저 또는 findAll먼저 실행될 수 있는데 그때 생기는 에러를 막기위해 db를 초기화 시킴
    void clearRepository(){
        rep.clear();

    }

    @Test
    void save(){
        Member member = new Member();
        member.setName("Hello");
        rep.save(member);

        Member result = rep.findByName("Hello").get();
        Assertions.assertEquals(member.getName(),result.getName()); //(기대값, 실제값)이 들어가서 확인해보는 assertEquals
    }
    @Test
    void findAll(){
        Member member1 = new Member();
        member1.setName("1");
        rep.save(member1);

        Member member2 = new Member();
        member2.setName("2");
        rep.save(member2);

        Assertions.assertEquals(2, rep.findAll().size());
    }
}
