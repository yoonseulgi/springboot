package com.koscom.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {
	//테스트 코드가 아닌 실제 데이터베이스를 사용해도 구동이 되는지 검사하기.
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
