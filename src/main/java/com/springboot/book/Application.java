package com.springboot.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication
 * 프로젝트의 메인클래스
 * - 스프링 부트의 자동설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정해줌
 * - 프로젝트의 최상단에 위치해야함
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        /**
         * SpringApplication.run(Application.class, args);
         * - 내장 WAS 를 실행 (별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것을 의미)
         * - 톰캣설치 필요없음
         * - 언제 어디서나 같은 환경에서 스프링 부트 배포가 가능하게됨
         */
        SpringApplication.run(Application.class, args);
    }

}
