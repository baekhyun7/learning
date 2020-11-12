package com.zh.learning.entity;

/**
 * api常量
 *
 * @author ydh
 */
public class ApiConstants {

    private ApiConstants() {
    }

    public static final String CODE = "ret_code";
    public static final String MSG = "ret_msg";
    public static final String DATA = "ret_data";

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGEE = "成功";
    public static final String FAIL_MESSAGEE = "失败";

    public static final int PARAMETER_ERROR_CODE = 400;
    public static final String PARAMETER_ERROR_MESSAGEE = "参数错误";

    public static final int AUTHENTICATION_ILLEGAL_CODE = 401;
    public static final String AUTHENTICATION_ILLEGAL_MESSAGE = "用户未登录";
    public static final String TOKEN_ILLEGAL_MESSAGE = "用户未登录";

    public static final int AUTHORIZATION_ILLEGAL_CODE = 406;
    public static final String AUTHORIZATION_ILLEGAL_MESSAGE = "用户权限不足";
    public static final String AUTH_ILLEGAL_MESSAGE = "用户权限不足";

    public static final int RESOURCE_NOT_FOUND_CODE = 404;
    public static final String RESOURCE_NOT_FOUND_MESSAGE = "接口不存在";

    public static final int PARAMETER_UNSUPPORT_CODE = 415;
    public static final String PARAMETER_UNSUPPORT_MESSAGE = "请求方法不支持";

    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "服务器内部出错";

    /**
     * 默认验证码时效10分钟
     */
    public static final int DEFAULT_AUTHCODE_TIMEOUT = 600;

    /**
     * 默认邮件验证码时效30分钟
     */
    public static final int EAMIL_AUTHCODE_TIMEOUT = 1800;

    /**
     * 默认邮件验证码时效5分钟
     */
    public static final int PHONE_AUTHCODE_TIMEOUT = 3600;

    /**
     * 默认cookie有效期1小时
     */
    public static final int DEFAULT_COOKIE_TIMEOUT = 3600;

    /**
     * 默认token失效时间为1小时。
     */
    public static final int DEFAULT_TOKEN_TIMEOUT = 3600;

    /**
     * 登录失败5次，默认半小时后才可继续登录。
     */
    public static final int DEFAULT_TIMES_TIMEOUT = 1800;

    /**
     * 登录失败3次，默认必须传入验证码。
     */
    public static final int MAX_NEED_CODE_TIMES = 3;

    /**
     * 登录失败10次，默认半小时后才可继续登录。
     */
    public static final int MAX_DEFAULT_TIMES = 10;

    /**
     * 登录失败次数后缀
     */
    public static final String TIMES = "times";

    /**
     * 默认token后缀
     */
    public static final String TOKEN = "zh_token";

    /**
     * 默认姓名后缀
     */
    public static final String NAME = "learning_name";

    /**
     * 默认邮箱后缀
     */
    public static final String EMAIL = "learning_email";

    /**
     * 验证码最大长度
     */
    public static final int CODE_MAXLENGTH = 5;

    /**
     * 用户冻结标志
     */
    public static final int FREEZE_CODE = 1;

    /**
     * 列表最大搜索数量，返回最新的数据
     */
    public static final int MAX_SHOW_ITEM_SIZE = 10000;

    /**
     * 默认首次登录修改密码出错次数后缀
     */
    public static final String PASS_TIMES_SUF = "web.forgetPassword";


    /**
     * 忘记密码出错次数后缀
     */
    public static final String FORGET_PASS_TIMES_SUF = "forget";

    /**
     * 发送邮件次数后缀
     */
    public static final String EMAIL_TIMES_SUF = "send.email.num";

    /**
     * 邮件验证码后缀
     */
    public static final String EMAIL_CODE_SUF = "send.email.code";

    /**
     * ip登录失败10次，默认半小时后才可继续登录。
     */
    public static final int IP_MAX_DEFAULT_TIMES = 10;



    /**
     * 发送短信次数后缀
     */
    public static final String PHONE_TIMES_SUF = "send.phone.num";
}
