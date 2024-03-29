package com.chelsea.springcloud2.flowable;

import org.flowable.ui.modeler.conf.DatabaseConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.chelsea.springcloud2.flowable.conf.AppDispatcherServletConfiguration;
import com.chelsea.springcloud2.flowable.conf.ApplicationConfiguration;

@Import(value = {
// 引入修改的配置
        ApplicationConfiguration.class, AppDispatcherServletConfiguration.class,
        // 引入 DatabaseConfiguration 表更新转换
        DatabaseConfiguration.class})
// Eureka 客户端
@ComponentScan(basePackages = {"com.chelsea.springcloud2.flowable.*"})
@MapperScan("com.chelsea.springcloud2.flowable.dao")
// 移除 Security 自动配置
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
