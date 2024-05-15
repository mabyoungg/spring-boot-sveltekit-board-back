package com.ll.likelionspringboottestmedium.domain.post.post.dto;

import com.ll.likelionspringboottestmedium.standard.base.HasConcreteDto;

public interface PostDto extends AbsPostDto, HasConcreteDto {
    String getBody();
}