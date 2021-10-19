package com.koscom.springboot.web;


import com.koscom.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller - jsp쓰듯 서버 렌더링된 파일 즉, html 파일을 만들어주는 페이지 파일을 사용할 때 쓰는 API
@RestController //http json을 응답으로 줄 수 있는 형태
public class HelloController {

    //hello라는 주소로 입력이 들어오면 hello가 반환되는 코드
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        // new 객체명 - 드래그 -> 우클릭 -> 리팩토링 -> introduce vari.. 누르면 변수화 됨.
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        System.out.println("dto = " + dto);
        return dto;
    }

}
