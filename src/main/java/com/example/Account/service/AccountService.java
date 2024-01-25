package com.example.Account.service;

import com.example.Account.domain.Account;
import com.example.Account.domain.AccountStatus;
import com.example.Account.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// repository 파일 : 뭔가 CRUD 동작의 시작점? 틀을 정의해 놓는 느낌
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private String noFinal;

      @Transactional
    public void createAccount(Long userId, Long initialBalance) {

    }

    @Transactional
    public Account getAccount(Long id) {
          if(id < 0) {
            throw new RuntimeException("Minus");
          }
        return accountRepository.findById(id).get();
    }
}
