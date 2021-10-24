package com.koscom.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@DataJpaTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //모든 테스트가 시작하기 전
//    @BeforeEach
//    void setUP() {
//
//    }
    //모든 테스트가 끝난 후
    //전 테스트가 다음 테스트에 영향을 주지 않기 위해 항상 초기화된 상태를 맨듦
    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    void 게시글저장_불러오기() {
        String title = "테스트 타이틀";
        String content = "테스트 본문";
        //저장하는 거 검증
        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .build());
        // 저장 값 불러오는 거 검증
        List<Posts> result = postsRepository.findAll();
        System.out.println(result.get(0).getId()); //db가 맹글어준 값
        System.out.println(result.get(0).getTitle()); //내가 맨든 값
        
        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(content);

    }

    @Test
    void 게시글저장_불러오기2() {
        String title = "테스트 타이틀";
        String content = "테스트 본문";
        //저장하는 거 검증
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());
        // 저장 값 불러오는 거 검증
        List<Posts> result = postsRepository.findAll();

        System.out.println(result.size());
        assertThat(result).hasSize(1);

    }

    @Test
    void 게시글저장_불러오기3() {
        String title = "테스트 타이틀";
        String content = "테스트 본문";
        //저장하는 거 검증
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .build());
        // 저장 값 불러오는 거 검증
        List<Posts> result = postsRepository.findAll();

        System.out.println(result.size());
        assertThat(result).hasSize(1);

    }

}
