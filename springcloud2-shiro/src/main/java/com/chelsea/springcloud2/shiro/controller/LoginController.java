package com.chelsea.springcloud2.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {
    
    @RequestMapping("/")
    public String toLogin(HttpServletRequest request) {
        log.debug("------toLogin");
        return "login";
    }
    
    @RequestMapping("/login")
    public String login() {
        log.debug("------login");
        return "login";
    }
    
    @PostMapping("/adminlogin")
    public String adminLogin(String username, String password, @RequestParam(defaultValue = "0") Boolean rememberMe, HttpServletRequest request) {
        try {
            // 添加用户认证信息
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
                // 进行验证，这里可以捕获异常，然后返回对应信息
                subject.login(token);
            }
        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            request.setAttribute("error", "用户名或密码错误");
            request.setAttribute("username", username);
            return "login";
        }
        return "redirect:/admin/index";
    }
    
}
