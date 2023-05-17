package com.springboot.book.domain.posts;

import com.springboot.book.web.dto.PostsListResponseDto;
import com.springboot.book.web.dto.PostsResponseDto;
import com.springboot.book.web.dto.PostsSaveRequestDto;
import com.springboot.book.web.dto.PostsUpdateRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        // JPA 영속성 컨텍스트 때문에 DB 쿼리를 직접 보내지 않고도 업데이트가 가능하다.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    // readonly 의 경우 트랜잭션 범위는 유지하되 조회속도 개선해줌.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
            .map(PostsListResponseDto::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없음"));
        postRepository.delete(posts);
    }
}
