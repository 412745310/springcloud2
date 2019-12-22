package com.chelsea.springcloud2.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class SampleController {

    @Value("${user.name}")
    String userName;

    @Value("${user.age}")
    int age;
    
    @RequestMapping(value = "/echo")
    public String echo() {
            return userName + "," + age;
    }

}
