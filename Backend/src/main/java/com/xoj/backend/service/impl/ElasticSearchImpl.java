package com.xoj.backend.service.impl;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.service.ElasticSearchService;
import com.alibaba.fastjson.JSON;
import com.xoj.backend.entity.Question;
import com.xoj.backend.entity.UserBase;
import com.xoj.backend.util.TransUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
/***
 * @Author jianghanchen
 * @Date 14:15 2022/3/27
 ***/

@Service
@SuppressWarnings("all")
public class ElasticSearchImpl implements ElasticSearchService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public RestResponse<?> createIndex(String index){
        CreateIndexRequest request = new CreateIndexRequest(index);
        try{
            restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            return RestResponse.ok();
        }catch (IOException e){
            return RestResponse.error();
        }
    }

    public RestResponse<?> isIndexExist(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        try {
            restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            return RestResponse.ok();
        }catch (IOException e){
            return RestResponse.error();
        }
    }

    public RestResponse<?> delete(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try {
            restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            return RestResponse.ok();
        }catch (IOException e){
            return RestResponse.error();
        }
    }

    public RestResponse<?> insertDocument(String index, String id, Object obj){
        IndexRequest request = new IndexRequest(index);
        String md5 = TransUtils.getMd5(id);
        if(md5 != null){
            request.id(md5);
            request.timeout(TimeValue.timeValueSeconds(10));
            request.source(JSON.toJSONString(obj), XContentType.JSON);
            try {
                restHighLevelClient.index(request, RequestOptions.DEFAULT);
            }catch (IOException e){
                return RestResponse.error();
            }
            return RestResponse.ok();
        }else {
            return RestResponse.error();
        }

    }

    public RestResponse<?> deleteDocument(String index, String id) {
        id = TransUtils.getMd5(id);
        DeleteRequest request = new DeleteRequest(index, id);
        request.timeout("1s");
        try {
            restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            return RestResponse.ok();
        }catch (IOException e){
            return RestResponse.error();
        }
    }


    public RestResponse<List<?>> searchDocument(String index, String field, String context, int from, int size) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(field, context);

        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search;
        try{
             search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            return RestResponse.error();
        }
        SearchHits hits = search.getHits();
        ArrayList<Question> questions = new ArrayList<>();

        for (SearchHit documentFields : hits.getHits()) {
            String jsonString = JSON.toJSONString(documentFields.getSourceAsMap());
            Question question = JSON.parseObject(jsonString, Question.class);
            questions.add(question);
        }

        return RestResponse.ok(questions);
    }

    public RestResponse<List<?>> searchDocument(String index, String field, String context) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(field, context);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search;
        try{
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            return RestResponse.error();
        }
        SearchHits hits = search.getHits();

        HashMap<Long, Question> map = new HashMap<>();
        for (SearchHit documentFields : hits.getHits()) {
            String jsonString = JSON.toJSONString(documentFields.getSourceAsMap());
            Question question = JSON.parseObject(jsonString, Question.class);
            map.put(question.getId(),question);
        }
        fuzzySearchDocument(index,field,context,map);
        ArrayList<Question> questions = new ArrayList<>(map.values());
        return RestResponse.ok(questions);
    }

    public void fuzzySearchDocument(String index, String field, String context, HashMap<Long, Question> map) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery(field, context);

        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.query(wildcardQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search;
        try{
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            return;
        }
        SearchHits hits = search.getHits();


        for (SearchHit documentFields : hits.getHits()) {
            String jsonString = JSON.toJSONString(documentFields.getSourceAsMap());
            Question question = JSON.parseObject(jsonString, Question.class);
            if(!map.containsKey(question.getId())){
                map.put(question.getId(),question);
            }
        }

        return;
    }



}
