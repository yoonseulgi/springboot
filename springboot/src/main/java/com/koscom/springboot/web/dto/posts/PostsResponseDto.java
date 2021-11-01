package com.koscom.springboot.web.dto.posts;


import com.koscom.springboot.domain.posts.Posts;
import javafx.geometry.Pos;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//조회용과 코어 테이블, 클래스 등을 따로 관리함으로 데이터를 지킬 수 있다.
public class PostsResponseDto {
    private Long id; // pk(auto increment, bigint)
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}
