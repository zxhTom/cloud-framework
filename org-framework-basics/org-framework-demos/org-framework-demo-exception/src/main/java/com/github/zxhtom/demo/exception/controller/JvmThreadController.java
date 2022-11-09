package com.github.zxhtom.demo.exception.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/jvm")
public class JvmThreadController {
    List<byte[]> memoryList = new ArrayList<>();

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10,
            15,
            2,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    @GetMapping("/memoryTest")
    public String memoryTest(@RequestParam  int c) {
        /*byte[] b = new byte[c * 1024 * 1024];
        memoryList.add(b);*/
        return "success";
    }


    @GetMapping("/cpuUsageRate")
    public String cpuUsageRate() {
       /* executor.submit(() -> {
            int i = 0;
            while (true) {
                i = i++ * 10 + 5;
                System.out.println(i);
            }
        });*/
        return "success";
    }


    @GetMapping("/threadLock")
    public String threadLock() {
        /*Object resourceA = new Object();
        Object resourceB = new Object();
        executor.submit(() -> {
            synchronized (resourceA) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceB) {
                }
            }
        });
        executor.submit(() -> {
            synchronized (resourceB) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceA) {
                }
            }
        });*/
        return "success";
    }
}
