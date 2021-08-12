package com.zh.learning.service.impl.rabbitMq;

import com.zh.learning.service.rabbitMq.MessageConsumer;
import com.zh.learning.service.rabbitMq.MessageProvide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zh
 * @date 2021-07-19 16:21
 */
@Service
@Slf4j
public class MessageConsumerImpl implements MessageConsumer {
    @Autowired
    RabbitTemplate template;

//    @RabbitListener(queuesToDeclare = @Queue(value = "duanxin.fanout.queue"))
//    @RabbitHandler
    @Override
    public void receiveMessage(String message) {
        log.info("1收到消息队列得消息：" + message);
    }

//    @RabbitListener(queuesToDeclare = @Queue(value = "duanxin.fanout.queue"))
//    @RabbitHandler
    @Override
    public void receive1Message(String message) {
        log.info("2收到消息队列得消息：" + message);
    }
}
