package com.chelsea.springcloud2.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        log.debug("------index");
        return "admin/index";
    }
    
}
