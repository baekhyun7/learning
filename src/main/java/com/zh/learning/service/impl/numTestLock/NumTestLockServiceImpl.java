package com.zh.learning.service.impl.numTestLock;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.learning.dao.sys.NumTestDao;
import com.zh.learning.entity.po.sys.NumTestPo;
import com.zh.learning.service.numTestLock.NumTestLockService;
import org.springframework.stereotype.Service;

/**
 * @author zh
 * @date 2021-07-13 11:23
 */
@Service
public class NumTestLockServiceImpl extends ServiceImpl<NumTestDao, NumTestPo> implements NumTestLockService {

}
