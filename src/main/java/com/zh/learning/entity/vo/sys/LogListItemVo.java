package com.zh.learning.entity.vo.sys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/** 
 *
 * @Author zh
 * @Description 日志数据
 * @Date 14:20 2020/11/13
 **/
@Data
public class LogListItemVo {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String userEmail;
    private String ip;
    private String resStatus;
    private String uri;
    private String operation;
    private String msg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
