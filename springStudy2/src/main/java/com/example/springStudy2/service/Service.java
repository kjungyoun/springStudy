package com.example.springStudy2.service;

import com.example.springStudy2.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.springStudy2.repository.Repository;

import java.util.List;
import java.util.Optional;

public class Service {
//    Repository repo;
//
//    public Service(Repository repo){
//        this.repo = repo
//    }
// 원래는 이런식으로 의존성을 주입해야 함

    private Repository repository;

    public Service(Repository repository){
        this.repository = repository;
    }
    public Long join(Member member){
        validate(member); //ifPresent는 if문과 동일하게 동작하므로 true일 경우 내부가 동작한다.
        repository.save(member);
        return member.getId();
    }

    private void validate(Member member) {
        repository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("Already Exist");
        });

    }
    public Optional<Member> findOne(Long id){
        return repository.findById(id);
    }
    public Optional<Member>findMemberByname(String name){return repository.findByName(name);}
    //Optional은 객체이므로 객체를 리턴한다. 따라서 이 함수의 리턴타입은 Optional이다.
    public List<Member> findMembers(){
        return repository.findAll();
    }
}
