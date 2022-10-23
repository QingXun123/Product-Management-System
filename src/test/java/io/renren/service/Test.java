package io.renren.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class Test {
    @Autowired
    private RedisTemplate redisTemplate;

    @org.junit.Test
    public void tt() {

    }
}
