package com.chelsea.springcloud2.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chelsea.springcloud2.event.producer.EventDemoPublish;

@RestController
@RequestMapping("/event")
public class EventController {
    
    @Autowired
    private EventDemoPublish eventDemoPublish;
    
    @RequestMapping("/test")
    public void test(String message) {
        eventDemoPublish.publish(message);
        System.out.println("send message : " + message);
    }

}
