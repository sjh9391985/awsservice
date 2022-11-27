package com.springboot.book.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본생성자 자동추가, public Posts(){}
@Entity
public class Posts {

    @Id // Table PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false) // 꼭 작성하지 않아도 되지만, 기본값 외 추가 변경옵션이 필요한 경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /*
    * 생성자와 빌더는 생성시점에 값을 채워주는 역할은 같음
    * 다만, 생성자의 경우 채워야 할 필드가 무엇인지 명확히 지정할 수 없음
    * */

    @Builder // 해당 클래스 빌더 패턴 클래스 생성
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
