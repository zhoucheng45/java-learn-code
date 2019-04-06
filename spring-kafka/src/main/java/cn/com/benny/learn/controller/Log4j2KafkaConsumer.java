/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-12-24 下午9:09
 * LastModified: 18-12-23 下午4:19
 */

package cn.com.benny.learn.controller;

import cn.com.benny.learn.dao.TestOrderMapper;
import cn.com.benny.learn.models.TestOrder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {"log4j2-kafka"})
public class Log4j2KafkaConsumer {

    @Autowired
    TestOrderMapper orderMapper;

    @KafkaHandler
    public void processMessage(String content) {
        System.out.println("消息被消费 log4j2-kafka " + content);
//        TestOrder order = JSONObject.parseObject(content, TestOrder.class);
//        orderMapper.insertSelective(order);

    }
}
