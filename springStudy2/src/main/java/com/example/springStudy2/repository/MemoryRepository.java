package com.example.springStudy2.repository;

import com.example.springStudy2.domain.Member;

import java.util.*;

public class MemoryRepository implements Repository{ //repository 인터페이스를 구현하는 class
    private static Map<Long, Member> db = new HashMap<>(); //key값을 id로 하고 Member에 들어있는 모든 값을 저장하는 것 = map (여기서는 db같은 역할을 한다.)
    private Long sequence = 0L; //사용자가 아이디를 직접 입력하는게 아닌 들어올 때마다 자동으로 번호를 매기는 역할 즉, id값 (처음 값 = 0)

    @Override
    public Member save(Member member) { //실제로 저장하는 동작을 구현
        member.setId(sequence);
        db.put(sequence, member);
        sequence += 1;
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(db.get(id)); //nullable은 null이나 값으로 리턴이 될 수 있는 동작
    }

    @Override
    public Optional<Member> findByName(String name) {
        return db.values().stream().filter(member -> member.getName().equals(name)).findAny();
        // db.values는 member를 의미하는데 이를 stream화 하여 filter를 사용하는데 filter내에서 m이라는 인자의 name이 내가 가져온 name과 같은 것을
        // Optional형태로 첫번째 것만 찾아서 return을 해줌
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(db.values()); //db에 있는 모든 값을 ArrayList로 반환을 해줌

    }

    public void clear(){
        db.clear(); //db에 저장된 정보를 다 날리는 메소드
    }
}
