package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final private String TABLE = "Member11";

    public Optional<Member> findById(Long id){

        var sql = String.format("SELECT * FROM %s WHERE id = :id",TABLE);
        var param = new MapSqlParameterSource()
                .addValue("id",id);

        RowMapper<Member> rowMapper = (ResultSet resultSet,int rowNum) -> Member
                .builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getNString("email"))
                .nickname(resultSet.getNString("nickname"))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .createAt(resultSet.getObject("createAt", LocalDateTime.class))
                .build();
        var member = namedParameterJdbcTemplate.queryForObject(sql,param,rowMapper);
        return Optional.ofNullable(member);
    }

    public Member save(Member member) {
        /*
        *
        * */

        if(member.getId()==null) return insert(member);

        return update(member);
    }

    private Member insert(Member member) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("Member11")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Member.builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .createAt(member.getCreateAt())
                .build()
                ;
    }
    private Member update(Member member) {
        //TODO: implement

        var sql = String.format("UPDATE %s set email = :email, nickname = :nickname, birthday = :birthday where id =:id",TABLE);
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);
        namedParameterJdbcTemplate.update(sql,parameterSource);
        return member;
    }
}
