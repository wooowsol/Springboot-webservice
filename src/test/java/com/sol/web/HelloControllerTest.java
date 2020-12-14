package com.sol.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class) // 테스트시 JUnit에 내장된 실행자 외에 다른 실행자를 실행.
// SpringRunner라는 스프링 실행자를 실행 -> 서버 구동 역할
// 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중. @Controller , @ControllerAdvice 등을 사용.
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 빈(bean-객체)을 주입받음.
    private MockMvc mvc; // 웹 API를 테스트 할 때 사용. 스프링 MVC 테스트 시작점

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //mvc.perform의 결과 검증. HTTP Header의 Status를 검증.(반환코드) 200 = 성공, 이외의 값이면 문제
                .andExpect(content().string(hello)); // 응답 본문의 내용 검증. (json이면 json 내용 페이지면 페이지 전체 코드)
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        System.out.println(123);
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // param("키", 값).  API 테스트할 때 사용될 요청 파라미터를 설정함. 단, 값은 String만 허용(숫자/ 날짜 데이터도 문자열로)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시.
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
