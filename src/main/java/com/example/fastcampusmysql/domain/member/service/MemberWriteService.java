package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService {
    final private MemberRepository memberRepository;
    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    public Member create(RegisterMemberCommand command) {
        /*
        * 목표 - 회원정보(이메일,닉네이,생년월일) 등록
        * 닉넴은 10자 이하
        * 파라미터 - memberRegisterCommand
        * val member = Member.of(memberRegisterCommand)
        * memberrepository.save(member)
        * */

        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();

        var savedMember = memberRepository.save(member);
        saveMemberNicknameHistory(savedMember);
        return memberRepository.save(member);
    }
    public void changNickname(Long id, String nickname) {

        var member = memberRepository.findById(id).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        saveMemberNicknameHistory(member);
    }

    private void saveMemberNicknameHistory(Member member) {
        var history = MemberNicknameHistory
                .builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }
}
