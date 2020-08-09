package com.example.springStudy2.repository;

import com.example.springStudy2.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcRepository implements Repository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource); //jdbctemplate에 dataSource를 넣어 이를 사용
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); // db에 값을 넣어주는 역할을 해주는 것
        jdbcInsert.withTableName("members").usingGeneratedKeyColumns("id"); // generatedKeyColumn은 auto increment가 되는 key값을 의미한다.

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name",member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jdbcTemplate.query("select * from members where id = ?",rowMapper(), id).stream().findAny(); // ctrl + alt + n 을 하면 아래 코드를 이처럼 한줄로 표현해준다.

    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from members where name = ?",rowMapper(), name); // 쿼리를 날렸을 때 나오는 결과인 resultSet을 rowMapper()가 받아서 처리해준다.
        return result.stream().findAny(); // 이름이 같은 사람이 여러개일 때 첫번째 사람만 가져오는 것
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from members", rowMapper());
    }

    private RowMapper<Member> rowMapper(){
        return new RowMapper<Member>() { //RowMapper라는 인터페이스를 구현한 클래스를 리턴한다.
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException { //이 안에서 클래서를 구현을 해놓기 때문에 인터페이스를 굳이 생성하지 않아도 된다.
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };
    }
}
