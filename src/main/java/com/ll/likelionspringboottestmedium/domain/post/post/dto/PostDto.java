package com.ll.likelionspringboottestmedium.domain.post.post.dto;

import lombok.Value;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Value
public class PostDto implements AbsPostDto {
    @NonNull
    private final long id;
    @NonNull
    private final LocalDateTime createDate;
    @NonNull
    private final LocalDateTime modifyDate;
    @NonNull
    private final long authorId;
    @NonNull
    private final String authorUsername;
    @NonNull
    private final String title;
    @NonNull
    private final String body;
}