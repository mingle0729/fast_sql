package com.example.fastcampusmysql.domain.member;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.util.MemberFlxtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class MemberTest {
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public void testChange (){
        var member = MemberFlxtureFactory.create();
        var toChangeName = "ppp";
        member.changeNickname(toChangeName);
        Assertions.assertEquals(toChangeName,member.getNickname());
//        LongStream.range(0,10)
//                .mapToObj(MemberFlxtureFactory::create)
//                .forEach(member ->{System.out.println(member.getNickname());});
    }
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public void testNicknameMaxLength(){
        var member = MemberFlxtureFactory.create();
        var overMaxLengthName = "pupppppppppupupupp";
        member.changeNickname(overMaxLengthName);
        Assertions.assertThrows(IllegalAccessException.class,()-> {
            member.changeNickname(overMaxLengthName);
        });
    }
}
