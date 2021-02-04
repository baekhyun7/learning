package com.zh.learning.controller.sys;

import com.zh.learning.constants.ApiConstants;
import com.zh.learning.controller.BaseController;
import com.zh.learning.entity.ResponseEntity;
import com.zh.learning.service.sys.LogService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zh
 * @date 2020
 **/
@Api(tags = "日志")
@RestController
@RequestMapping("/api/system")
@Slf4j
public class LogsController extends BaseController {

    private LogService logsService;

    @Autowired
    public LogsController(LogService logsService) {
        this.logsService = logsService;
    }

    /**
     * 获取日志列表
     */
    @RequiresPermissions("log")
    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public ResponseEntity getLogsList(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "beginDate", required = true) String beginDate,
                                      @RequestParam(value = "endDate", required = true) String endDate,
                                      @RequestParam(value = "loginName", required = false) String loginName,
                                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        //针对前端传空值进行的转换，因为还有其他转换所以就不在xml里设置了
        if (StringUtils.isEmpty(loginName)) {
            loginName = null;
        }

        Date begin = null, end = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (beginDate != null) {
                begin = dateFormat.parse(beginDate);
            }
            if (endDate != null) {
                end = dateFormat.parse(endDate);
            }
        } catch (ParseException e) {
            log.error("获取日志审计列表 : dateFormat.parse() exception");
            e.printStackTrace();
            return ResponseEntity.fail(ApiConstants.PARAMETER_ERROR_CODE, ApiConstants.PARAMETER_ERROR_MESSAGEE);
        }

        return ResponseEntity.success("日志查询成功", logsService.getLogsList(begin, end, loginName, pageNum, pageSize));
    }

    /**
     * 导出日志
     */
    @RequestMapping(value = "/logs/download", method = RequestMethod.GET)
    public Object downloadLogs(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "beginDate", required = true) String beginDate,
                               @RequestParam(value = "endDate", required = true) String endDate,
                               @RequestParam(value = "code", required = false) Integer code,
                               @RequestParam(value = "loginName", required = false) String loginName,
                               @RequestParam(value = "label", required = false) String label) {
        //针对前端传空值进行的转换，因为还有其他转换所以就不在xml里设置了
        if (StringUtils.isEmpty(loginName)) {
            loginName = null;
        }
        if (StringUtils.isEmpty(label)) {
            label = null;
        }

        Date begin = null, end = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (beginDate != null) {
                begin = dateFormat.parse(beginDate);
            }
            if (endDate != null) {
                end = dateFormat.parse(endDate);
            }
        } catch (ParseException e) {
            log.error("导出日志 : dateFormat.parse() exception");
            return ResponseEntity.fail(ApiConstants.PARAMETER_ERROR_CODE, ApiConstants.PARAMETER_ERROR_MESSAGEE);
        }

        response.reset(); //清除buffer缓存
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + "Logs" + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //导出Excel对象
        XSSFWorkbook workbook = logsService.downloadLogs(begin, end, loginName);
        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("导出日志 : OutputStream exception");
            return ResponseEntity.fail(ApiConstants.PARAMETER_ERROR_CODE, ApiConstants.PARAMETER_ERROR_MESSAGEE);
        }
        return ResponseEntity.success("日志查询成功");
    }

}
