package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private long time;
    @Before
    public void beginTime(){
        this.time = System.currentTimeMillis();
    }

    @Test
    public void contextLoads(){

    }

    @After
    public void endTime(){
        System.err.println(System.currentTimeMillis() - time);
    }
}
