package com.chelsea.springcloud2.elasticsearch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chelsea.springcloud2.elasticsearch.property.ElasticSearchProperties;
import com.chelsea.springcloud2.elasticsearch.util.ElasticSearchUtil;

@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
public class ComponentAutoConfigure {
    
    @Autowired
    private ElasticSearchProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public ElasticSearchUtil elasticSearchUtil (){
        return new ElasticSearchUtil();
    }

}
