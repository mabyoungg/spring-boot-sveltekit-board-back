package com.ll.likelionspringboottestmedium.domain.post.post.repository;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.post.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop30ByPublishedOrderByIdDesc(boolean isPublished);

    Page<Post> findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(String kw, String kw_, Pageable pageable);

    List<Post> findByPublishedOrderByIdDesc(boolean isPublished);

    List<Post> findByAuthorOrderByIdDesc(Member author);
}
