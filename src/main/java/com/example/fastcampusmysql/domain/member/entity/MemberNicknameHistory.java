package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class MemberNicknameHistory {
    final private Long id;
    final private Long memberId;
    final private String nickname;
    final private LocalDateTime createAt;


    @Builder
    public MemberNicknameHistory(Long id, Long memberId, String nickname, LocalDateTime createAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createAt = createAt == null ? LocalDateTime.now(): createAt;
    }
}