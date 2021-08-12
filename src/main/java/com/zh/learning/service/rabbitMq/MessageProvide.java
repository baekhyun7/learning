package com.zh.learning.service.rabbitMq;

/**
 * @author zh
 * @date 2021-07-19 16:19
 */
public interface MessageProvide {

    /**
     * 发送消息
     * @param message
     */
    void SendMessage(String message);

}
