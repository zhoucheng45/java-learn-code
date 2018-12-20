package cn.com.benny.learn.controller;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {"test"})
public class KafkaConsumer {

    @KafkaHandler
    public void processMessage(String content) {
        System.out.println("消息被消费"+content);
    }
}
