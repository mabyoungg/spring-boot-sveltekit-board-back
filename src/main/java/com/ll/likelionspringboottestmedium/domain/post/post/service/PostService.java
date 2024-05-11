package com.ll.likelionspringboottestmedium.domain.post.post.service;

import com.ll.likelionspringboottestmedium.domain.memeber.memeber.entity.Member;
import com.ll.likelionspringboottestmedium.domain.post.post.entity.Post;
import com.ll.likelionspringboottestmedium.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void write(Member author, String title, String body, boolean isPublished) {
        Post post = Post.builder()
                .author(author)
                .title(title)
                .body(body)
                .published(isPublished)
                .build();

        postRepository.save(post);
    }

    public List<Post> findTop30ByPublishedOrderByIdDesc(boolean isPublished) {
        return postRepository.findTop30ByPublishedOrderByIdDesc(isPublished);
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public Page<Post> search(String kw, Pageable pageable) {
        return postRepository.findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCase(kw, kw, pageable);
    }

    public <T> List<T> findByPublished(boolean isPublished, Class<T> type) {
        return postRepository.findByPublishedOrderByIdDesc(isPublished, type);
    }

    public <T> List<T> findByAuthor(Member author, Class<T> type) {
        return postRepository.findByAuthorOrderByIdDesc(author, type);
    }

    public <T> Optional<T> findById(long id, Class<T> type) {
        return postRepository.findById(id, type);
    }
}
