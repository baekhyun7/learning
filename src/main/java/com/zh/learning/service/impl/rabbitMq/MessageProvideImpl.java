package com.zh.learning.service.impl.rabbitMq;

import com.zh.learning.service.rabbitMq.MessageProvide;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zh
 * @date 2021-07-19 16:21
 */
@Service
@Slf4j
public class MessageProvideImpl implements MessageProvide {
    @Autowired
    RabbitTemplate template;

    @Override
    public void SendMessage(String message) {
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        template.convertAndSend(exchangeName,routingKey,message);
    }
}
