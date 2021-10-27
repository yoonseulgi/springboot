package com.koscom.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//junit5로 테스트 코드를 짤건데 spring으로 테스트 짤거다
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //적절한 bin이 주입됨.
    @Autowired
    MockMvc mvc;

    //test에서만 한글로 메소드 명을 입력할 수 있다.
    @Test
    void hello주소로_요청이_오면_hello가_리턴된다() throws Exception {
        String expectResult = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectResult));
    }

    @Test
    void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

      //$ : 클래스 괄호의 시작을 나타냄.
    }

    //필수 변수 값 하나가 없으면 400대 에러가 뜰거야 라는 코드를 문서화하는 과정
    //개발하다가 발생하는 이슈를 만나면 그 이슈에 맞는 test 코드를 작성하면 됨.
    //에러가 400번대인 이슈는 테스트가 되었다!ㅇ
    @Test
    void amount가없으면_응답코드400이_리턴된다() throws Exception {
        String name = "hello";

        mvc.perform(get("/hello/dto")
                        .param("name", name))
                .andExpect(status().isBadRequest());


        //$ : 클래스 괄호의 시작을 나타냄.
    }
}
