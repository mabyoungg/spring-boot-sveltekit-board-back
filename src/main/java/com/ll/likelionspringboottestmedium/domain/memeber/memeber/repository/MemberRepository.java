package com.ll.likelionspringboottestmedium.domain.memeber.memeber.repository;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
