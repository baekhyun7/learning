package com.zh.learning.service.rabbitMq;

/**
 * @author zh
 * @date 2021-07-19 16:19
 */
public interface MessageConsumer {

    /**
     * 接受消息
     *
     * @param message
     */
    void receiveMessage(String message);
    void receive1Message(String message);

}
