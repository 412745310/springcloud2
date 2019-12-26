package com.chelsea.springcloud2.elasticsearch.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.chelsea.springcloud2.elasticsearch.property.ElasticSearchProperties;

@Slf4j
public class ElasticSearchUtil {

    private static ElasticSearchProperties elasticSearchProperties;

    @Autowired
    public void setElasticSearchProperties(ElasticSearchProperties properties) {
        elasticSearchProperties = properties;
    }

    private static class RestHighLevelClientSingleton {
        private static final RestHighLevelClient client;
        static {
            List<HttpHost> nodesList = new ArrayList<>();
            String nodes = elasticSearchProperties.getClusterNodes();
            String[] nodesArr = nodes.split(",");
            for (String node : nodesArr) {
                String ip = node.split(":")[0];
                int port = Integer.parseInt(node.split(":")[1]);
                nodesList.add(new HttpHost(ip, port));
            }
            HttpHost[] httpHost = new HttpHost[nodesList.size()];
            RestClientBuilder restClientBuilder = RestClient.builder(nodesList.toArray(httpHost));
            client = new RestHighLevelClient(restClientBuilder);
        }
    }

    public static RestHighLevelClient getClient() {
        return RestHighLevelClientSingleton.client;
    }

    /**
     * 新增文档，同步操作
     * 
     * @param index
     * @param source
     * @throws IOException
     */
    public void createDocument(String index, Map<String, Object> source) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index).source(source);
        IndexResponse indexResponse = getClient().index(indexRequest, RequestOptions.DEFAULT);
        log.info("id->" + indexResponse.getId());
        log.info("index->" + indexResponse.getIndex());
        log.info("type->" + indexResponse.getType());
        log.info("version->" + indexResponse.getVersion());
        log.info("result->" + indexResponse.getResult());
        log.info("shardInfo->" + indexResponse.getShardInfo());
    }

    /**
     * 新增文档，异步操作
     * 
     * @param index
     * @param source
     */
    public void createDocumentAsync(String index, Map<String, Object> source) {
        IndexRequest indexRequest = new IndexRequest(index).source(source);
        getClient().indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {

            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }

            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("id->" + indexResponse.getId());
                log.info("index->" + indexResponse.getIndex());
                log.info("type->" + indexResponse.getType());
                log.info("version->" + indexResponse.getVersion());
                log.info("result->" + indexResponse.getResult());
                log.info("shardInfo->" + indexResponse.getShardInfo());
            }

        });
    }

    /**
     * 搜索
     * 
     * @param index
     * @param field
     * @param value
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> search(String index, String field, Object value) throws IOException {
        // 类包含关系  SearchRequest -> SearchSourceBuilder -> QueryBuilder
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery(field, value));
        searchRequest.source(sourceBuilder);
        SearchResponse search = getClient().search(searchRequest, RequestOptions.DEFAULT);
        log.info("搜索到 " + search.getHits().getTotalHits().value + " 条数据.");
        SearchHits hits = search.getHits();
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }
}
