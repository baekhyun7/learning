package com.zh.learning.controller.elasticSearch;

import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.service.es.ElasticSearchService;
import com.zh.learning.vo.User;
import com.zh.learning.vo.request.ElasticSearchAddIndexReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zh
 * @date 2021/7/8
 */
@Api(tags = "ES操作")
@RestController
@RequestMapping(value = "/es")
public class ElasticSearchController extends BaseController {

    @Autowired
    ElasticSearchService elasticSearchService;

    @ApiOperation(value = "查询索引")
    @GetMapping(value = "/getIndex")
    public ResponseEntity getIndex(@RequestParam(value = "indexName") String indexName) {
        Object index = elasticSearchService.getIndex(indexName);
        return ResponseEntity.success(index);
    }

    @ApiOperation(value = "查询索引下的数据")
    @GetMapping(value = "/getIndex/getDocs")
    public ResponseEntity getDocs(
            @ApiParam(value = "指标") @RequestParam(value = "indexName") String indexName,
            @ApiParam(value = "关键字") @RequestParam(value = "keyWord", required = false) String keyWord,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize) {
        List<User> all = elasticSearchService.getAll(indexName, keyWord, pageNum, pageSize);
        return ResponseEntity.success(all);
    }

    @ApiOperation(value = "添加索引")
    @PostMapping(value = "/addIndex")
    public ResponseEntity addIndex(@RequestParam(value = "indexName") String indexName) {
        boolean b = elasticSearchService.addIndex(indexName);
        if (!b) {
            return ResponseEntity.success("添加索引失败");
        }
        return ResponseEntity.success("添加索引成功");
    }

    @ApiOperation(value = "删除索引")
    @DeleteMapping(value = "/deleteIndex")
    public ResponseEntity deleteIndex(@RequestParam(value = "indexName") String indexName) {
        boolean b = elasticSearchService.deleteIndex(indexName);
        if (!b) {
            return ResponseEntity.success("删除索引失败");
        }
        return ResponseEntity.success("删除索引成功");
    }

    @ApiOperation(value = "新增文档")
    @PostMapping(value = "/addDoc")
    public ResponseEntity addDoc(@RequestBody ElasticSearchAddIndexReq req) {
        Object o = elasticSearchService.addDoc(req);
        return ResponseEntity.success(o);
    }
}
