package com.chelsea.springcloud2.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.chelsea.springcloud2.event.event.EventDemo;

/**
 * 生产者
 * 
 * @author shevchenko
 *
 */
@Component
public class EventDemoPublish {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String message) {
        EventDemo demo = new EventDemo(this, message);
        applicationEventPublisher.publishEvent(demo);
    }
    
}
