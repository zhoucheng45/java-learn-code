/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-11-13 下午11:15
 * LastModified: 18-11-13 下午11:15
 */

package cn.com.benny.learn;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver1 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }

}