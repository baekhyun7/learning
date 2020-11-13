package com.zh.learning.service.impl.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.learning.constants.ApiConstants;
import com.zh.learning.entity.po.sys.LogPo;
import com.zh.learning.dao.sys.LogDao;
import com.zh.learning.entity.vo.sys.LogListItemVo;
import com.zh.learning.service.sys.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.learning.util.excel.ExcelBean;
import com.zh.learning.util.excel.ExportExcel;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    LogDao logDao;
    @Override
    public PageInfo<LogListItemVo> getLogsList(Date beginDate,
                                               Date endDate,
                                               String loginName,
                                               Integer pageNum,
                                               Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogListItemVo> sysLogPoList = logDao.queryWithParams(false, beginDate, endDate, loginName);
        PageInfo<LogListItemVo> pageBean = new PageInfo<>(sysLogPoList);

        return pageBean;
    }

    @Override
    public XSSFWorkbook downloadLogs(Date beginDate,
                                     Date endDate,
                                     String loginName) {
        List<LogListItemVo> sysLogPoList = logDao.queryWithParams(true, beginDate, endDate, loginName);
        return exportExcelInfo(sysLogPoList);
    }

    public XSSFWorkbook exportExcelInfo(List logs) {
        //根据条件查询数据，把数据装载到一个list中
        List<ExcelBean> excel = new ArrayList<>();
        Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook = null;
        //设置标题栏
        excel.add(new ExcelBean("操作", "title", 0));
        excel.add(new ExcelBean("用戶名", "userName", 0));
        excel.add(new ExcelBean("邮箱", "userEmail", 0));
        excel.add(new ExcelBean("操作ip", "ip", 0));
        excel.add(new ExcelBean("访问接口", "requestUri", 0));
        excel.add(new ExcelBean("时间", "createDate", 0));
        excel.add(new ExcelBean("操作结果", "msg", 0));
        map.put(0, excel);
        String sheetName = "日志";
        //调用ExcelUtil的方法
        try {
            xssfWorkbook = ExportExcel.createExcelFile(LogListItemVo.class, logs, map, sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
    }
}
