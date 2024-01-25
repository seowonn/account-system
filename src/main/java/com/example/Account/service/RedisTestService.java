package com.example.Account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTestService {
    private final RedissonClient redissonClient;

    public String getLock() {
        RLock lock = redissonClient.getLock("sampleLock");

        try {
            // unlock이 없으면 무조건 3초동안 내가 갖고 쓰지 않게 된다.
            boolean isLock = lock.tryLock(1, 5, TimeUnit.SECONDS);
            if(!isLock) {
                log.error("======Lock acquisition failed=====");
                return "Lock failed";
            }
        } catch (Exception e) {
            log.error("Redis lock failed");
        }
        return "Lock success";
    }
}
