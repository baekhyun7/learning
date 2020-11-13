package com.zh.learning.dao.sys;

import com.zh.learning.entity.po.sys.LogPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.learning.entity.vo.sys.LogListItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zh
 * @since 2020-11-12
 */
@Mapper
@Repository
public interface LogDao extends BaseMapper<LogPo> {

    /**
     * 根据指定条件查询
     *
     * @param download  是否为下载，是则查询最新的1000条，而不采用分页的形式
     * @param beginDate
     * @param endDate
     * @param loginName
     * @return
     */
    List<LogListItemVo> queryWithParams(@Param("download") Boolean download,
                                        @Param("beginDate") Date beginDate,
                                        @Param("endDate") Date endDate,
                                        @Param("loginName") String loginName
                                        );

}
