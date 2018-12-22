/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-11-14 上午12:35
 * LastModified: 18-11-14 上午12:34
 */

package cn.com.benny.learn;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messages")
public class HelloReceiver3 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver3  : " + hello);
    }

}