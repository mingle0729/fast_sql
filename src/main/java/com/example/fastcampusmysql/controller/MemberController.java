package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    final  private MemberWriteService memberWriteService;
    final  private MemberReadService memberReadService;
    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        var member = memberWriteService.create(command);
        return memberReadService.toDto(member);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PostMapping("/members/{id}/name")
    public MemberDto changNickname(@PathVariable Long id, @RequestBody String nickname) {

        memberWriteService.changNickname(id,nickname);
        return memberReadService.getMember(id);
    }
    @PostMapping("/members/{memberId}/history")
    public List<MemberNicknameHistoryDto> getNicknameHistroties(@PathVariable Long memberId) {
        return memberReadService.getNicknameHistories(memberId);
    }
}
