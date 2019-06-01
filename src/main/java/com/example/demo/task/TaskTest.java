package com.example.demo.task;

import com.example.demo.user.dto.request.RegisterRequest;
import com.example.demo.user.service.UserService;
import com.example.demo.util.StringTools;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TaskTest {

    @Resource
    private UserService userService;

    @Scheduled(cron = "0/5 * 0-22 * * ?")//每天7点到22点5秒钟执行一次
    public void circle(){
        System.err.println("//每天7点到22点5秒钟执行一次");
        RegisterRequest registerRequest = new RegisterRequest();
        String mobile = StringTools.getMobileStr();
        registerRequest.setEmail(mobile + "@qq.com");
        registerRequest.setMobile(mobile);
        registerRequest.setName("用户"+mobile);
        registerRequest.setPassword("a25dfdg5fdglj5dsd325gg");
        userService.register(registerRequest);//注册机器粉^_^
    }

    @Scheduled(cron = "0 40 22 * * ?")//每天22点40分执行该定时任务
    public void fixedTimes(){
        System.err.println("//每天22点40分执行该定时任务");
    }
}
