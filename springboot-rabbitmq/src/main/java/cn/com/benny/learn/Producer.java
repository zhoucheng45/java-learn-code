/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 19-4-6 下午10:24
 * LastModified: 19-4-6 下午8:44
 */

package cn.com.benny.learn;

import cn.com.benny.learn.cfg.RabbitCfg;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>生产者:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019/4/5
 * @Time: 16:19
 */

@RestController
@RequestMapping("/producer")
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name ){

        amqpTemplate.convertAndSend(RabbitCfg.EXCHANGE,RabbitCfg.DELAY_QUEUE, "mq测试数据:"+name,
                new ExpirationMessagePostProcessor(3000L));
        return name;

    }



}
