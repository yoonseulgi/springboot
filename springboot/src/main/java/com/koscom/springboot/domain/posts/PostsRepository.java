package com.koscom.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;


//Postrs, long -> 대상이 되는 entity, pk타입
//Jpa~를 상속 받으면 기본 CRUD가 자동 구현된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
