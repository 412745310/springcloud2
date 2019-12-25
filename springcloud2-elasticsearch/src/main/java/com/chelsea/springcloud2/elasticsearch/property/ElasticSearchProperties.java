package com.chelsea.springcloud2.elasticsearch.property;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("elasticsearch")
public class ElasticSearchProperties {
    
    private String clusterNodes;

}
