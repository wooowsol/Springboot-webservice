package com.sol.web.dto;

import com.sol.web.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 조회 API 테스트
@Getter
public class PostsResponseDto { // Entity의 필드 중 일부만 사용하므로 생성자로  Entity를 받아 필드에 값을 넣고 처리.

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}