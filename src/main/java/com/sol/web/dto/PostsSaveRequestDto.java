package com.sol.web.dto;

import com.sol.web.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto { // Entity(도메인) 클래스와 유사한 Dto 클래스 생성. Entity 클래스를 Request/Response 클래스로 사용하는 것은 매우 안좋음.
// Request/Response용 Dto는 View를 위한 클래스라 정말 자주 변경하므로 View Layer와 DB Layer의 역할 분리한다.
// Controller에서 결과값으로 여러 테이블을 조인해서 줘야할 경우가 빈번하므로 되도록 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용.
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
