package com.example.Account.dto;

import lombok.*;

import java.time.LocalDateTime;

// 계좌 생성 과정에서 사용되는 요청과 응답 항목을 저장하는 공간
public class CreateAccount {
    @Getter
    @Setter
    public static class Request {
        private Long userId;
        private Long initialBalance;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long userId;
        private String accountNumber;
        private LocalDateTime registeredAt;
    }
}
