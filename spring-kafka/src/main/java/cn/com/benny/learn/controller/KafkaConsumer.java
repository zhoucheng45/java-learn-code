package cn.com.benny.learn.controller;

import cn.com.benny.learn.dao.TestOrderMapper;
import cn.com.benny.learn.models.TestOrder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {"order"})
public class KafkaConsumer {

    @Autowired
    TestOrderMapper orderMapper;

    @KafkaHandler
    public void processMessage(String content) {
        System.out.println("消息被消费" + content);
        TestOrder order = JSONObject.parseObject(content, TestOrder.class);
        orderMapper.insertSelective(order);

    }
}
