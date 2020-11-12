package com.zh.learning.service.impl.sys;

import com.zh.learning.entity.po.sys.LogPo;
import com.zh.learning.dao.sys.LogDao;
import com.zh.learning.service.sys.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zh
 * @since 2020-11-12
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, LogPo> implements LogService {

}
