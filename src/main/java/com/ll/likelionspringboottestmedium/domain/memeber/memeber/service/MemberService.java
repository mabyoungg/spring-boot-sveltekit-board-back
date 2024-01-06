package com.ll.likelionspringboottestmedium.domain.memeber.memeber.service;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.memeber.memeber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(String username, String password) {
        Member member = new Member(username, password);
        memberRepository.save(member);

        return member;
    }
}
