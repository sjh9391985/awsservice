package com.springboot.book.domain;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createDate, modifiedDate를 자동으로 관리함.
 *
 */

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 컬럼으로 인식해줌
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능을 포함시킴
public class BaseTimeEntity {

    @CreatedDate // 생성시간 자동생성
    private LocalDateTime createDate;

    @LastModifiedDate // 수정시간 자동생성
    private LocalDateTime modifiedDate;

}
