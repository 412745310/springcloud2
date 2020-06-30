package com.chelsea.springcloud2.mybatisplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    
    /**
     * 定义分页插件
     * 默认不定义的话，mybatisplus分页为内存分页，也就是全部查到内存，再分页
     * 定义之后为物理分页，也就是SQL语句中会有limit
     * 
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
