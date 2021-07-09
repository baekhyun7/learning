package com.zh.learning.service.es;

import com.zh.learning.vo.User;
import com.zh.learning.vo.request.ElasticSearchAddIndexReq;
import com.zh.learning.vo.request.ElasticSearchReq;
import com.zh.learning.vo.request.ElasticSearchUpdateIndexReq;

import java.util.List;

/**
 * es操作
 *
 * @author zh
 * @date 2021/7/8
 */
public interface ElasticSearchService {
    /**
     * 添加索引，重复创建会抛出相关异常
     *
     * @param indexName
     * @return
     */
    boolean addIndex(String indexName);

    /**
     * 查询索引
     *
     * @param indexName
     * @return
     */
    Object getIndex(String indexName);

    /**
     * 删除索引，索引不存在会抛出相关异常
     *
     * @param indexName
     * @return
     */
    boolean deleteIndex(String indexName);

    /**
     * 新增文档记录
     *
     * @param req
     * @return
     */
    Object addDoc(ElasticSearchAddIndexReq req);

    /**
     * 修改文档记录
     *
     * @param req
     * @return
     */
    Object updateDoc(ElasticSearchUpdateIndexReq req);

    /**
     * 删除文档记录
     *
     * @param req
     * @return
     */
    Object deleteDoc(ElasticSearchReq req);

    List<User> getAll(String indexName,
                      String keyWord,
                      Integer pageNum,
                      Integer pageSize);
}
