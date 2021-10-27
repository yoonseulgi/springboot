package com.koscom.springboot.service;

import com.koscom.springboot.domain.posts.Posts;
import com.koscom.springboot.domain.posts.PostsRepository;
import com.koscom.springboot.web.dto.posts.PostsUpdateRequestDto;
import com.koscom.springboot.web.dto.posts.PostssaveRequestDto;
import javafx.geometry.Pos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final로 선언된 필드들은 생성자 항목으로 자동 포함시켜 줌
//즉, @Autowired를 선언하지 않아도 자동으로 해줌.
@Service //spring bean 등록 & service 클래스 선언
public class PostsService {

/*
    @Autowired를 사용하는 3가지 방법

1.  @Autowired
    PostsRepository postsRepository;
but, 권장하지 않는 방법.

2.  @Autowired
    public void setPostsRepository(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

3.  public PostsService(@Autowired PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    2, 3번도 귀찮음. spring이 점점 발전하면서
    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }
    생성자 항목에 들어가 있으면 자동으로 @Auto가 선언되게 됨!

    */

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostssaveRequestDto requestDto) {
        Posts posts = postsRepository.save(requestDto.toEntity());
        return posts.getId();
    }

    //수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto dto) {
        //이때 디비에서 가져온 값을 jpa 캐시에 캐싱을 해둠
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자는 없습니다. id = "+ id));

        entity.update(dto.getTitle(), dto.getContent());
        /*아래와 같이 DB에 저장하는 코드가 없다.
        postsRepository.save(entity);
        dirty checking
        캐싱된 값과 객체의 값이 다를 때 자동으로 디비에 적용시킴.
         */
        return entity.getId();
    }
}
