package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberNicknameHistoryRepository {
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    static final private String TABLE = "MemberNicknameHistory";

    static final RowMapper<MemberNicknameHistory> rowMapper = (ResultSet resultSet, int rowNum) -> MemberNicknameHistory
            .builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .nickname(resultSet.getString("nickname"))
            .createAt(resultSet.getObject("createAt", LocalDateTime.class))
            .build();
    public List<MemberNicknameHistory> findById(Long memberId){

        var sql = String.format("SELECT * FROM %s WHERE memberId = :memberId",TABLE);
        var param = new MapSqlParameterSource()
                .addValue("memberId",memberId);

        return namedParameterJdbcTemplate.query(sql,param,rowMapper);
    }
    public MemberNicknameHistory save(MemberNicknameHistory history) {

        if(history.getId()==null) return insert(history);

        throw new UnsupportedOperationException("갱신x");
    }

    private MemberNicknameHistory insert(MemberNicknameHistory history) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("MemberNicknameHistory")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(history);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return MemberNicknameHistory.builder()
                .id(id)
                .memberId(id)
                .nickname(history.getNickname())
                .createAt(history.getCreateAt())
                .build()
                ;
    }
}
