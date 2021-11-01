package com.koscom.springboot.domain.posts;

import com.koscom.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor  //디폴트 생성자 - 생성자를 정의해주면 디폴트 생성자가 사라져서 명시해줌
@Entity
public class Posts extends BaseTimeEntity {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 채번 방식
    private Long id; // pk(auto increment, bigint)

    @Column(length = 500, nullable = false) //varchar(500), not null
    private String title;

    @Column(length = 2000, nullable = false) //varchar(2000), not null
    private String content;


    private String author; // @Column없으면 varchar(255)가 디폴트, nullable = true 가 디폴트


    @Builder // lombok의 builder
    //id 필드가 없는 생성자
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //title과 content만 수정 가능하다. author은 수정가능하지 않다.
    //수정일자도 신규로 생성된다.
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
