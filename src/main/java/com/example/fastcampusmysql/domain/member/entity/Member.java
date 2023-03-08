package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    final private Long id;

    private String nickname;
    final private String email;
    final private LocalDate birthday;
    final private LocalDateTime createAt;

    final private static Long NAME_MAX_LENGTH = 10L;

    @Builder
    public Member(Long id, String nickname, String email, LocalDate birthday, LocalDateTime createAt) {
        this.id = id;
        validateNickname(nickname);
        this.nickname = Objects.requireNonNull(nickname);
        this.email = Objects.requireNonNull(email);
        this.birthday = Objects.requireNonNull(birthday);
        this.createAt = createAt == null ? LocalDateTime.now(): createAt;
    }

    public void changeNickname(String other) {
        Objects.requireNonNull(other);
        validateNickname(other);
        nickname = other;
    }
    private void validateNickname(String nickname) {
        Assert.isTrue(nickname.length()<=NAME_MAX_LENGTH,"최대 길이를 초과했습니다.");
    }
}
