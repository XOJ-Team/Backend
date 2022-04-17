package com.xoj.backend.service;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.Question;

import java.util.List;

/***
 * @Author jianghanchen
 * @Date 14:12 2022/3/27
 ***/
public interface ElasticSearchService {

    RestResponse<?> createIndex(String index);

    RestResponse<?> isIndexExist(String index);

    RestResponse<?> delete(String index);

    RestResponse<?> insertDocument(String index, String id, Object obj);

    RestResponse<?> deleteDocument(String index, String id);

    RestResponse<List<?>> searchDocument(String index, String name, String context, int from, int size);

    RestResponse<List<?>> searchDocument(String index, String name, String context);

}
