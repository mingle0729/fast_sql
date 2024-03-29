package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowReadService {
    final private FollowRepository followRepository;

    public List<Follow> getFollowings(Long memberId) {
        return followRepository.findAllByFromMeberId(memberId);
    }


    public List<Follow> getFollowers(Long memberId) {
        return followRepository.findAllByToMemberId(memberId);
    }
}
