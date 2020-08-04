package com.example.springStudy2.repository;

import com.example.springStudy2.domain.Member;

import java.util.List;
import java.util.Optional;

public interface Repository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Optional은 id에 해당하는 값이 없을 경우 null이 아닌 이해하기 쉬운 것을 반환
    Optional<Member> findByName(String name);
    List<Member> findAll(); //list는 배열같은 역할
}
