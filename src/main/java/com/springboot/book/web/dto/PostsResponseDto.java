package com.springboot.book.web.dto;

import com.springboot.book.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    /*
    * PostsResponseDto는 Entity 필드 중 일부만 사용하기에 생성자로 Entity를 받아 필드에 값을 넣음.
    * */
    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }



}
