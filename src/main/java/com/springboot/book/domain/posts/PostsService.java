package com.springboot.book.domain.posts;

import com.springboot.book.web.dto.PostsResponseDto;
import com.springboot.book.web.dto.PostsSaveRequestDto;
import com.springboot.book.web.dto.PostsUpdateRequestDto;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(("해당 게시글이 없습니다.")));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
}
