package com.sol.web.domain.posts;

import com.sol.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드 자동생성 ( Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.)
// Setter는 단순히 값을 세팅하는 것이기에 명확하게 목적과 의도를 나타내주지 못하기 때문. ex) order.setStatus(false); -> order.cancleOrder();
@NoArgsConstructor // 기본 생성자 자동 추가
@Entity // 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름 매칭 ex) SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙을 나타낸다. 스프링부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment 됨.
    private long Id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼 나타냄. 기본값 외 추가 변경이 필요한 옵션에 사용. ex) 문자열, VARCHAR(255) 기본값 -> 사이즈 500 변경
    private String title;

    @Column( columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래의 빌더 패턴 클래스 생성. 생성자 상단에 선언시 생성자에 포함된 빌드만 빌더에 포함 ( 어느 필드에 어떤 값을 채워야 할지 명확함)
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
