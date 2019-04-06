/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 19-4-6 下午10:24
 * LastModified: 19-4-6 下午10:12
 */

package cn.com.benny.learn;

import cn.com.benny.learn.cfg.RabbitCfg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>消费者:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019/4/6
 * @Time: 15:38
 */
@Component
@Slf4j
public class Consumer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 可以直接通过注解声明交换器、绑定、队列。但是如果声明的和rabbitMq中已经存在的不一致的话
     * 会报错便于测试，我这里都是不使用持久化，没有消费者之后自动删除
     * {@link RabbitListener}是可以重复的。并且声明队列绑定的key也可以有多个.
     *
     * @param headers
     * @param msg
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = RabbitCfg.EXCHANGE),
                    value = @Queue(value = RabbitCfg.PROCESS_QUEUE),
                    key = RabbitCfg.PROCESS_QUEUE),
            //手动指明消费者的监听容器，默认Spring为自动生成一个SimpleMessageListenerContainer
            containerFactory = "containerFactory",
            //指定消费者的线程数量,一个线程会打开一个Channel，一个队列上的消息只会被消费一次（不考虑消息重新入队列的情况）,下面的表示至少开启5个线程，最多10个。线程的数目需要根据你的任务来决定，如果是计算密集型，线程的数目就应该少一些
            concurrency = "5-10"
    )
    /**
     * 在上面也看到了@Payload等注解用于注入消息。这些注解有：
     *
     * @Header 注入消息头的单个属性
     * @Payload 注入消息体到一个JavaBean中
     * @Headers 注入所有消息头到一个Map中
     */
    public void process(@Headers Map<String, Object> headers, @Payload Message msg){

        log.info(headers.toString());
        String s = msg.getMessageProperties().getExpiration() == null ? ((HashMap<String, String>) ((ArrayList) headers.get("x-death")).get(0)).get("original-expiration") : msg.getMessageProperties().getExpiration();
        Long count = (Long) (((HashMap<String, Object>) ((ArrayList) headers.get("x-death")).get(0)).get("count"));
        int i = Integer.valueOf(s) * (2<<count);
        msg.getMessageProperties().setExpiration(String.valueOf(i));

        amqpTemplate.convertAndSend(RabbitCfg.EXCHANGE,RabbitCfg.DELAY_QUEUE,msg);

    }

    public static void main(String[] args) {
        double pow = Math.pow(2.0, 3.0);
        System.out.println(2<<3);
    }
}
