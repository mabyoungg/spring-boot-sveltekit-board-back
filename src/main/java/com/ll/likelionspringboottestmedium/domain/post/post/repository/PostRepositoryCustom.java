package com.ll.likelionspringboottestmedium.domain.post.post.repository;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.post.post.dto.PostListItemDto;
import com.ll.likelionspringboottestmedium.standard.base.KwTypeV1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    <T extends PostListItemDto> Page<T> findByKw(KwTypeV1 kwType, String kw, Member author, Boolean published, Pageable pageable, Class<T> type);
}
