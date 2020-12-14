package com.sol.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get 메소드 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성. final 없는 필드는 생성자에 포함 안됨. 즉, 생성자를 생성하고 생성자를 통해서 값을 주입 받는 것.
public class HelloResponseDto {

    private final String name;
    private final int amount;
}