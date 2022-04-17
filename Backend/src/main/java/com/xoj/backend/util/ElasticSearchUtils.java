package com.xoj.backend.util;

import com.alibaba.fastjson.JSON;
import com.xoj.backend.entity.Question;
import com.xoj.backend.entity.UserBase;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 * @Author jianghanchen
 * @Date 0:33 2022/3/27
 ***/
@SuppressWarnings("all")
public class ElasticSearchUtils {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public boolean createIndex(String index){
        CreateIndexRequest request = new CreateIndexRequest(index);
        try{
            restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public boolean isIndexExist(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        try {
            return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            return false;
        }
    }

    public boolean delete(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try {
            restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            return true;
        }catch (IOException e){
            return false;
        }
    }

    public boolean createDocument(String index, Object obj) throws IOException{
        IndexRequest request = new IndexRequest(index);
        String md5 = null;
        if(obj instanceof Question){
            md5 = TransUtils.getMd5(((Question)obj).getName());
        }else if(obj instanceof UserBase){
            md5 = TransUtils.getMd5(((UserBase)obj).getName());
        }
        if(md5 != null){
            request.id(md5);
            request.timeout(TimeValue.timeValueSeconds(10));
            request.source(JSON.toJSONString(obj), XContentType.JSON);
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
            return true;
        }else {
            return false;
        }

    }

    public boolean deleteDocument(String index, String id) {
        DeleteRequest request = new DeleteRequest(index, id);
        request.timeout("1s");
        try {
            restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            return true;
        }catch (IOException e){
            return false;
        }
    }


    public List<Question> searchDocument(String index, String name, String context, int from, int size) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(name, context);

        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        ArrayList<Question> questions = new ArrayList<>();

        for (SearchHit documentFields : hits.getHits()) {
            String jsonString = JSON.toJSONString(documentFields.getSourceAsMap());
            Question question = JSON.parseObject(jsonString, Question.class);
            questions.add(question);
        }

        return questions;
    }

    public List<Question> searchDocument(String index, String name, String context) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(name, context);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = search.getHits();
        ArrayList<Question> questions = new ArrayList<>();

        for (SearchHit documentFields : hits.getHits()) {
            String jsonString = JSON.toJSONString(documentFields.getSourceAsMap());
            Question question = JSON.parseObject(jsonString, Question.class);
            questions.add(question);
        }

        return questions;
    }

}
