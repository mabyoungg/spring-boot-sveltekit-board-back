package com.ll.likelionspringboottestmedium.domain.memeber.memeber.repository;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    Optional<Member> findByRefreshToken(String refreshToken);
}
