package com.example.Account.controller;

import com.example.Account.domain.Account;
import com.example.Account.dto.AccountDto;
import com.example.Account.dto.CreateAccount;
import com.example.Account.service.AccountService;
import com.example.Account.service.RedisTestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final RedisTestService redisTestService;

    // 계좌 생성 시, 사용자 아이디와 초기 잔액을 POST로 보내기로 했음으로,
    // url이 아닌,
    // requestBody에 사용자 아이디와 초기 잔액을 담아서 보낸다.
    @PostMapping("/account")
    public CreateAccount.Response createAccount(
            @RequestBody @Valid CreateAccount.Request request
    ){
        return CreateAccount.Response.from(
            accountService.createAccount(
                    request.getUserId(),
                    request.getInitialBalance()
            )
        );
    }

    @GetMapping("/get-lock")
    public String getLock() {
        return redisTestService.getLock();
    }

    @GetMapping("/account/{id}")
    public Account getAccount(
            @PathVariable Long id) {
        return accountService.getAccount(id);
    }
}
