package com.example.springStudy2.repository;

import com.example.springStudy2.domain.Member;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaNewRepository implements Repository {

    private final EntityManager em;

    public JpaNewRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //  원하는 db를 확인 -> 값을 저장하는 sql을 자동으로 생성 ,영속적으로 저장하는 것 (영속적 = 변하지 않는)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // findById 메소드의 인자로 받은 id 값으로 member.class에서 값을 찾는 동작(find는 primary key로만 값을 찾을 수 있다.)
        return Optional.ofNullable(member); // member객체가 optional 타입으로 반환된다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name =:name", Member.class)
                .setParameter("name", name).getResultList();// JPQL 이라는 객체지향 쿼리를 사용해야 한다.
// select m(레코드 하나의 별명) from Member(객체명이자 테이블명) m 이런 형식이다.
        // em에 alt + enter를 하여 introduce local variable을 하면 자동으로 반환 타입을 명시해준다.
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();

    }
}
