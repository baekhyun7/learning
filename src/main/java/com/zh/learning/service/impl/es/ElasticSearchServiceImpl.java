package com.zh.learning.service.impl.es;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zh.learning.exception.ApiException;
import com.zh.learning.service.es.ElasticSearchService;
import com.zh.learning.vo.User;
import com.zh.learning.vo.request.ElasticSearchAddIndexReq;
import com.zh.learning.vo.request.ElasticSearchReq;
import com.zh.learning.vo.request.ElasticSearchUpdateIndexReq;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * es 操作
 *
 * @author zh
 * @date 2021/7/8
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    @Autowired
    RestHighLevelClient client;

    @Override
    public boolean addIndex(String indexName) {
        //构造请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
//        String mapping = "{\n" +
//                "      \"properties\" : {\n" +
//                "        \"address\" : {\n" +
//                "          \"type\" : \"text\",\n" +
//                "          \"analyzer\" : \"ik_max_word\"\n" +
//                "        },\n" +
//                "        \"age\" : {\n" +
//                "          \"type\" : \"long\"\n" +
//                "        },\n" +
//                "        \"name\" : {\n" +
//                "          \"type\" : \"keyword\"\n" +
//                "        }\n" +
//                "      }\n" +
//                "    }";
        CreateIndexResponse createIndexResponse;
        try {
            createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ApiException("es创建索引失败");
        }
        return createIndexResponse.isAcknowledged();
    }

    @Override
    public Object getIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest(indexName);
        GetIndexResponse getIndexResponse;
        try {
            getIndexResponse = client.indices().get(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ApiException("es创建索引失败");
        }
        return getIndexResponse.getMappings();
    }

    @Override
    public boolean deleteIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        AcknowledgedResponse delete;
        try {
            delete = client.indices().delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ApiException("es删除索引失败");
        }
        return delete.isAcknowledged();
    }

    @Override
    public Object addDoc(ElasticSearchAddIndexReq req) {
        //创建请求连接
        IndexRequest request = new IndexRequest(req.getIndexName());
        //自定义id
        request.id(req.getUser().getId());
        //请求超时时间
        request.timeout("10s");
        //将请求的数据放到request中，并指定消息类型为json
        request.source(JSON.toJSONString(req.getUser()), XContentType.JSON);
        //通过客户端传递请求
        IndexResponse index;
        try {
            index = client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ApiException("es删除索引失败");
        }
        return index;
    }

    @Override
    public Object updateDoc(ElasticSearchUpdateIndexReq req) {
        UpdateRequest request = new UpdateRequest(req.getIndexName(), req.getUser().getId());
        //请求超时时间
        request.timeout("10s");
        //将请求的数据放到request中，并指定消息类型为json
        request.doc(JSON.toJSONString(req.getUser()), XContentType.JSON);
        //通过客户端传递请求
        UpdateResponse response;
        try {
            response = client.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ApiException("es更新索引失败");
        }
        return response;
    }

    @Override
    public Object deleteDoc(ElasticSearchReq req) {
        DeleteRequest request = new DeleteRequest(req.getIndexName(), req.getId());
        DeleteResponse response;
        try {
            response = client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ApiException("es删除索引失败");
        }
        return response;
    }

    @Override
    public List<User> getAll(String indexName, String keyWord, Integer pageNum, Integer pageSize) {
        //2. 构建查询请求对象，指定查询的索引名称
        SearchRequest searchRequest = new SearchRequest(indexName);

        //3. 创建查询条件构建器SearchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //4.查询条件
        //4.1match_all查询
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        //4.2 匹配某个字段
//        MatchQueryBuilder query = QueryBuilders.matchQuery("name",keyWord);
        //4.3 term Query为精确查询，在搜索时会整体匹配关键字，不再将关键字分词。因为不分词，所以汉字只能查询一个字，英语是一个单词.
//        QueryBuilder query = QueryBuilders.termQuery("name.keyword",keyWord);
        //4.4 模糊查询
//        QueryBuilder query = QueryBuilders.wildcardQuery("name.keyword","*"+keyWord+"*");
        //boolQuery：对多个查询条件连接。
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        //2.构建各个查询条件
        //2.1 查询品牌名称为:华为
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", keyWord);
//        query.must(termQueryBuilder);

        //2.2. 查询标题包含：手机
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", keyWord);
        query.filter(matchQuery);

        //分页
        if (Objects.nonNull(pageNum)) {
            sourceBuilder.from((pageNum - 1) * pageSize);
        }
        if (Objects.nonNull(pageSize)) {
            sourceBuilder.size(pageSize);
        }
        //5.指定查询条件
        sourceBuilder.query(query);
        //添加查询条件构造器中
        searchRequest.source(sourceBuilder);

        //客户端去连接查询
        SearchResponse search;
        try {
            search = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ApiException("查询失败");
        }
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        List<User> userList = Lists.newArrayList();
        for (SearchHit searchHit : hits1) {
            String sourceAsString = searchHit.getSourceAsString();
            User user = JSON.parseObject(sourceAsString, User.class);
            userList.add(user);
        }
        return userList;
    }
}
