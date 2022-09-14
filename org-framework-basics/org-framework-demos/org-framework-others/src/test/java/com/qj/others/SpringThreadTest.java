package com.qj.others;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//必须加上webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT  否则websocket注册失败
@SpringBootTest(classes = OthersApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringThreadTest {
    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;
    @Test
    public void threadPoolsTest() throws InterruptedException {
        poolTaskExecutor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("我就是测试下");
            }
        });
        System.out.println("我是主线程");
        TimeUnit.SECONDS.sleep(100);
    }
}
