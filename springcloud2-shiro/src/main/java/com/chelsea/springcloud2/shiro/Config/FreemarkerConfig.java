package com.chelsea.springcloud2.shiro.Config;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import freemarker.template.TemplateModelException;

@Slf4j
@Configuration
public class FreemarkerConfig {
    
    @Autowired  
    protected freemarker.template.Configuration configuration;  

    @Autowired
    private ShiroTag shiroTag;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        // 注入全局配置到freemarker
        log.info("开始配置freemarker全局变量...");
        // shiro鉴权
        configuration.setSharedVariable("shiro", shiroTag);
        log.info("freemarker自定义标签配置完成!");
    }

}
