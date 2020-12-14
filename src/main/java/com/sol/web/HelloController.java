package com.sol.web;

import com.sol.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController 기존 스프링에서는  @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 함.
@RestController // 컨트롤러를 JSON으로 반환하는 컨트롤러로 만들어줌.(값을 반환해줌.)
public class HelloController {

    @GetMapping("/hello") //HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌
    //스프링에서는 @RequestMapping(method = RequsetMethod.GET)으로 사용했었다.
    public String hello() {

        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // @RequestParam 외부에서 API로 넘긴 파라미터를 가져옴.
                                     @RequestParam("amount")int amount){ //  "name" 이란 이름으로 넘긴 파라미터를 name 변수에 저장했음.
        return new HelloResponseDto(name, amount);
    }
}

