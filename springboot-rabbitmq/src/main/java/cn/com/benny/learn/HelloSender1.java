/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-11-13 下午11:20
 * LastModified: 18-11-13 下午11:20
 */

package cn.com.benny.learn;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender1 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String sendMsg = "hello1 " + new Date();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend("helloQueue", sendMsg);
    }

}