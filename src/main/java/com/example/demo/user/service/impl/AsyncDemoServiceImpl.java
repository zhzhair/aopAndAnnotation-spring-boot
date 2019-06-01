package com.example.demo.user.service.impl;

import com.example.demo.user.service.AsyncDemoService;
import org.springframework.stereotype.Service;

@Service
public class AsyncDemoServiceImpl implements AsyncDemoService {

    @Override
    public void asyncJob() {
        try {
            Thread.sleep(10_000);//睡觉10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
