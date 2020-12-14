package com.sol.web.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 해당 클래스를 상속받는 Entity 클래스들이 해당 클래스의 필드 값도 컬럼으로 인식하도록 함.
@EntityListeners(AuditingEntityListener.class) // 해당 애노테이션이 붙은 클래스에 auditing 기능을 추가함.
public abstract class BaseTimeEntity { // 모든 Entity의 상위 클래스로 Entity들의 createDate, modifiedDate를 자동으로 관리하는 역할이다.

    @CreatedDate // Entity가 생성될 때 생성시점의 시간이 해당 필드에 기록.
    private LocalDateTime createdDate;

    @LastModifiedDate // Entity가 수정될 때 위와 같은 기능. 처음 생성될 때의 시간도 기록됨.
    private LocalDateTime modifiedDate;
}
/*
 Auditing(감사)
  - 의심가는 데이터베이스의 작업을 모니터링 하고, 기록 정보를 수집 하는 기능.
  - 어느시간때에 어떤 작업들이 주로 발생하는지, 어떤 작업을 누가 하는지 추적 가능.
  - 감사 작업을 하면, 감사 로그를 기록해야 하므로 시스템의 속도는 더 느려짐.

 */