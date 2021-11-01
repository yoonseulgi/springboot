package com.koscom.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//auditing: createAt / updateAt / createUser / updateUser 자동으로 관리 해주는 기능
@Getter
@MappedSuperclass // (1)
@EntityListeners(AuditingEntityListener.class) // (2)
//하위 패키지가 상속받을 것이기 때문에 상위 패키지 안에 두어야 함.

public abstract class BaseTimeEntity {

    @CreatedDate // (3) 등록시간
    private LocalDateTime createdDate;

    @LastModifiedDate // (4) 수정시간
    private LocalDateTime modifiedDate;

}



