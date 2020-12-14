package com.sol.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //  Auditing 기능을 활성화 -> 최소 1개 엔티티가 있어야함
@SpringBootApplication // 스프링부트의 자동설정, 스프링 Bean 읽기, 생성을 모두 자동으로 설정.
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
// @SpringBootApplication이 위치한 클래스로부터 설정을 읽어나가기 때문에
// @SpringBootApplication 있는 클래스가 가장 최상단 디렉토리에 위치해야 한다.

// SpringApplication.run 을 이용하여 내장 WAS(spring)를 실행.
// 이경우 Tomcat을 사용할 필요가 없어 어떤 환경에서든지 단순 jar로 배포가능