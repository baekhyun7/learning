package com.zh.learning.service.sys;

import com.github.pagehelper.PageInfo;
import com.zh.learning.entity.po.sys.LogPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.learning.entity.vo.sys.LogListItemVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zh
 * @since 2020-11-12
 */
public interface LogService extends IService<LogPo> {


    /**
     * 获取日志审计列表<BR/>
     * 示例：
     * {<BR/>
     * "pageNum": 1,<BR/>
     * "pageSize": 10,<BR/>
     * "size": 10,<BR/>
     * "startRow": 1,<BR/>
     * "endRow": 10,<BR/>
     * "total": 737,<BR/>
     * "pages": 74,<BR/>
     * "list": [<BR/>
     * {<BR/>
     * "title": "系统登录",<BR/>
     * "ip": "0:0:0:0:0:0:0:1",<BR/>
     * "requestUri": "/auto-admin/sys/menu/tree",<BR/>
     * "createDate": "2017-09-15 23:29:10",<BR/>
     * "msg": "登录成功",<BR/>
     * "userName": "xx",<BR/>
     * "userEmail":"xx@changhong.com"<BR/>
     * }]<BR/>
     * }<BR/>
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @param loginName 用户名
     * @param pageNum   页码
     * @param pageSize  页大小
     * @return 页信息
     */
    PageInfo<LogListItemVo> getLogsList(Date beginDate,
                                        Date endDate,
                                        String loginName,
                                        Integer pageNum,
                                        Integer pageSize);

    /**
     * 导出excel
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @param loginName 用户名
     * @return excel对象
     */
    XSSFWorkbook downloadLogs(Date beginDate,
                              Date endDate,
                              String loginName);
}
