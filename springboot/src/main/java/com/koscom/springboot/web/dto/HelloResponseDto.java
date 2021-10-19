package com.koscom.springboot.web.dto;
//dto package: 외부 디비에서 데이터를 가지고 오거나 브라우저에서 들어오는 값 등 데이터를 다룰 떄 사용

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString //객체의 리턴 값을 바로 나타냄. 원래는 객체 주소가 반환되는데.
/* public String toString() {} 객체를 사용하는 것과 같은 효과*/
@Getter //선언된 변수의 get함수를 자동으로 만들어줌
@RequiredArgsConstructor //final이 선언되어있는 변수의 생성자를 자동으로 만들어줌
/* public HelloResponseDto(String name, int amount) {
        this.name = name;
        this.amount = amount;
   }  와 같은 역할을 수행
* */
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
