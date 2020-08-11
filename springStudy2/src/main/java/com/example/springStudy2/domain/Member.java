package com.example.springStudy2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {

    @Id  // Primary Key로 지정하는 에너테이션으로 바로 밑에 값만 영향을 받는 것 따라서 여기서는 id가 primary Key 이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Key값이 자동으로 생성되고 증가되게끔 해주는 것
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
