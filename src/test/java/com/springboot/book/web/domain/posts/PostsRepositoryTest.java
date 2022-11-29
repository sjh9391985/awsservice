package com.springboot.book.web.domain.posts;


import com.springboot.book.domain.posts.PostRepository;
import com.springboot.book.domain.posts.Posts;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 DB 자동으로 실행해줌
public class PostsRepositoryTest {

    @Autowired
    PostRepository postRepository;

    /**
     * @After
     * - Junit에서 단위테스트가 끝날 때마다 수행되는 메소드를 지정
     * - 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
     * - 여러 테스트 동시 수행시 테스트용 DB인 H2에 데이터가 그대로 남아 잇어 다음 테스트 실행 시 테스트 실패를 할 수 있음
     */
    @After
    public void cleanup(){
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(Posts.builder() // insert, update 쿼리를 실행함 (id[PK] 가 있으면 update, 없으면 insert 작업 수행)
            .title(title)
            .content(content)
            .author("sjh9391985@naver.com")
            .build());

        // when
        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }


    @Test
    public void BaseTimeEntity_등록 () {

        // given
        LocalDateTime now = LocalDateTime.of(2022,11,29,0,0,0);
        postRepository.save(Posts.builder()
            .title("title").content("content").author("author").build());

        // when
        List<Posts> postsList = postRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println("create : " + posts.getCreateDate());
        System.out.println("modify : " + posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
