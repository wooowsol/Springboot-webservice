package com.sol.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    // DAO를 JPA에서는 Repository라고 부른다. 인터페이스로 생성
    // 단순히 인터페이스를 생성한 후, JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨.
    // @Repository 추가할 필요없음. 다만, Entity 클래스와 기본 Entity Repository는 함께 위치해야 함. 함께 움직여야 하므로 도메인 패키지에서 함께 관리한다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}