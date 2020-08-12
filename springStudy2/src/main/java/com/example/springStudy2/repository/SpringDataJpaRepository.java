package com.example.springStudy2.repository;

import com.example.springStudy2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member,Long>, Repository {
    @Override
    Optional<Member> findByName(String name);


}
