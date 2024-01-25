package com.example.Account.domain;

import jakarta.persistence.*;
import lombok.*;

// 지금 DB의 테이블을 생성하고 있다고 생각하면된다.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {
    // PK 기본키 설정 과정
    @Id
    @GeneratedValue
    private Long id;
    // 이는 다 컬럼을 의미한다.
    private String accountNumber;

    // enum 값의 인덱스가 아닌 실제 문자열을 저장하게 된다.
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

}
