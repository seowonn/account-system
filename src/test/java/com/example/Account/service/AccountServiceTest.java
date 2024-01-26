package com.example.Account.service;

import com.example.Account.domain.Account;
import com.example.Account.type.AccountStatus;
import com.example.Account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
// Junit이라는 framework가 대신 돌려주기에, main이 아닌데도, 실행이 된다.
class AccountServiceTest {
    // @Autowired // SpringBootTest를 씀으로써
    // 사용하고자하는 클래스의 의존성에 상관없이 바로 사용할 수 있다.

    @Mock // mock을 사용하게 되면, 가짜 accountRepository를
    // 만들어 넣어주기 때문에 아래의 BeforeEach가 필요없게 된다.
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

//    @BeforeEach
//    void init() {
//        // accountService에 저장된 정보가 없으니까 하나를 먼저 저장해준다.
//        accountService.createAccount();
//    }

    @Test
    @DisplayName("계좌 조회 성공")
    void testXXX() {
        // given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder()
                                .accountStatus(AccountStatus.UNREGISTERED)
                                .accountNumber("65789")
                                .build()));
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);

        // when
        Account account = accountService.getAccount(4555L);

        // then
        verify(accountRepository, times(1)).findById(captor.capture());
        verify(accountRepository, times(0)).save(any());
        assertEquals(4555L, captor.getValue());
        assertNotEquals(45515L, captor.getValue());
        assertEquals("65789", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());
    }

    @Test
    @DisplayName("계좌 조회 실패 - 음수로 조회")
    void testFailedToSearchAccount() {
        // given
        // when
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> accountService.getAccount(-10L));

        // then
        assertEquals("Minus", runtimeException.getMessage());
    }

    @Test
    @DisplayName("Test 이름 변경")
    void testGetAccount() {
        // given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder()
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("65789")
                        .build()));

        // when
        Account account = accountService.getAccount(4555L);

        // then
        assertEquals("65789", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());
    }

    @Test
    void testGetAccount2() {
        // given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder()
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("65789")
                        .build()));

        // when
        Account account = accountService.getAccount(4555L);

        // then
        assertEquals("65789", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());
    }
}