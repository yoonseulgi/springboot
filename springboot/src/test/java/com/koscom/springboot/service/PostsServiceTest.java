package com.koscom.springboot.service;


import com.koscom.springboot.domain.posts.Posts;
import com.koscom.springboot.domain.posts.PostsRepository;
import com.koscom.springboot.web.dto.posts.PostsUpdateRequestDto;
import com.koscom.springboot.web.dto.posts.PostssaveRequestDto;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsService postsService;

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll(); // JPA 상태를 보고, 자식 테이블까지 삭제할지 결정
    }

    @Test
    void postsService를통해서_저장이된다() {
        String title = "test";
        String content = "test2";
        PostssaveRequestDto dto = PostssaveRequestDto.builder()
                .title("test")
                .content("test2")
                .build();
        postsService.save(dto);
        List<Posts> result = postsRepository.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(content);


    }

    @Test
    void postsService를통해서_수정이된다() {
        //수정될 값이 있어야 하기에 미리 저장된 값을 하나 생성해줌.
        Posts save = postsRepository.save(Posts.builder()
                        .title("1")
                        .content("2")
                        .build());

        System.out.println("save.title = " + save.getTitle());
        System.out.println("save.content = " + save.getContent());

        String title = "test";
        String content = "test2";

        PostsUpdateRequestDto dto = PostsUpdateRequestDto.builder()
                .title("test")
                .content("test2")
                .build();

        postsService.update(save.getId(), dto);

        List<Posts> result = postsRepository.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo(title);
        assertThat(result.get(0).getContent()).isEqualTo(content);


    }


    @Test
    void Postrs를_수정하면_수정시간이_갱신된다() {
        //수정될 값이 있어야 하기에 미리 저장된 값을 하나 생성해줌.
        Posts save = postsRepository.save(Posts.builder()
                .title("1")
                .content("2")
                .build());
        LocalDateTime beforeTime = save.getModifiedDate();

        System.out.println("beforeTime = " + beforeTime);


        PostsUpdateRequestDto dto = PostsUpdateRequestDto.builder()
                .title("test")
                .content("test2")
                .build();

        postsService.update(save.getId(), dto);

        List<Posts> result = postsRepository.findAll();
        LocalDateTime newTime = result.get(0).getModifiedDate();
        System.out.println("newtime = " + newTime);

        assertThat(newTime).isAfter(beforeTime);//2019년 보다 수정일이 후에 있는냐



    }

}
