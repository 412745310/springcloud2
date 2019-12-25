package com.chelsea.springcloud2.elasticsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chelsea.springcloud2.elasticsearch.util.ElasticSearchUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {

    @Autowired
    private ElasticSearchUtil elasticSearchUtil;

    /**
     * 新增文档，同步操作
     * 
     * @throws IOException
     */
    @Test
    public void createDocument() throws IOException {
        Map<String, Object> source = new HashMap<>();
        source.put("name", "张三");
        source.put("mail", "412745310@qq.com");
        source.put("age", 30);
        elasticSearchUtil.createDocument("itcast", source);
    }

    /**
     * 新增文档，异步操作
     * 
     * @throws InterruptedException
     */
    @Test
    public void createDocumentAsync() throws InterruptedException {
        Map<String, Object> source = new HashMap<>();
        source.put("name", "李四");
        source.put("mail", "903506988@qq.com");
        source.put("age", 28);
        elasticSearchUtil.createDocumentAsync("itcast", source);
        Thread.sleep(5000);
    }

    /**
     * 搜索
     * 
     * @throws IOException
     */
    @Test
    public void search() throws IOException {
        List<Map<String, Object>> list = elasticSearchUtil.search("itcast", "name", "张");
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}
