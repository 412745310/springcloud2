package com.chelsea.springcloud2.event.consumer;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.chelsea.springcloud2.event.event.EventDemo;

/**
 * 消费者
 * 
 * @author shevchenko
 *
 */
@Component
public class EventDemoListener implements ApplicationListener<EventDemo> {
    
    @Async
    @Override
    public void onApplicationEvent(EventDemo event) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("receiver message : " + event.getMessage());
    }

}
