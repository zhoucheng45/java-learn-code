/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-13 下午8:10
 * LastModified: 18-11-13 下午8:10
 */

package cn.com.benny.learn.mq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/13
 * @Time: 20:10
 */
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     *
     * @param msg
     * @return
     */
    public boolean send(String msg){

        try {
            amqpTemplate.convertAndSend(RabbitMqConfig.queue, msg);
            amqpTemplate.convertAndSend(RabbitMqConfig.topicExchange, RabbitMqConfig.routingKey, msg);
            return  true;
        } catch (AmqpException e) {
            e.printStackTrace();
            return false;
        }

    }

}
