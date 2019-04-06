/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 19-4-6 下午10:24
 * LastModified: 19-4-6 下午10:00
 */

package cn.com.benny.learn.cfg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019/4/5
 * @Time: 15:41
 */
@Configuration

public class RabbitCfg {

    private static Logger logger = LoggerFactory.getLogger(RabbitCfg.class);

    public static final String DELAY_QUEUE = "delay_queue";
    public static final String PROCESS_QUEUE = "process_queue";
    public static final String EXCHANGE = "process_exchange";


    /**
     * 延迟队列队列
     *
     * @return
     */
    @Bean("processQueue")
    public Queue queue() {
        Queue queue = QueueBuilder.durable(PROCESS_QUEUE).withArgument("","").build();
        return queue;
    }

    /**
     * 处理队列
     *
     * @return
     */
    @Bean("delayQueue")
    public Queue delayQueue() {
        Queue queue = QueueBuilder.durable(DELAY_QUEUE)
                .withArgument("x-dead-letter-exchange", EXCHANGE)
                .withArgument("x-dead-letter-routing-key", PROCESS_QUEUE).build();
        return queue;
    }

    /**
     * 交换机
     *
     * @return
     */
    @Bean("directExchange")
    public Exchange directExchange() {
        Exchange build = ExchangeBuilder.directExchange(EXCHANGE).build();
        return build;
    }

    @Bean
    public Binding bindProcessQueue(Queue processQueue, Exchange directExchange) {
        Binding noargs = BindingBuilder.bind(processQueue).to(directExchange).with(PROCESS_QUEUE).noargs();
        return noargs;
    }

    @Bean
    public Binding bindDelayQueue(Queue delayQueue, Exchange directExchange) {
        Binding noargs = BindingBuilder.bind(delayQueue).to(directExchange).with(DELAY_QUEUE).noargs();
        return noargs;
    }

//    /**
//     * 声明简单的消费者，接收到的都是原始的{@link Message}
//     *
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    SimpleMessageListenerContainer simpleContainer(ConnectionFactory connectionFactory, Consumer processReceiver) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setMessageListener(processReceiver::process);
//        container.setQueueNames(RabbitCfg.PROCESS_QUEUE);
//        return container;
//    }

    @Value("${spring.rabbitmq.host:localhost}")
    String host;
    @Value("${spring.rabbitmq.host:port:5672}")
    String port;
    @Value("${spring.rabbitmq.host:username:guest}")
    String username;
    @Value("${spring.rabbitmq.host:password:guest}")
    String pwd;
    @Value("${spring.rabbitmq.host:virtual-host:/}")
    String virtualHost;

//    @Bean("containerFactory")
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost(this.host);
//        connectionFactory.setPort(Integer.valueOf(this.port));
//        connectionFactory.setUsername(this.username);
//        connectionFactory.setPassword(this.pwd);
//        connectionFactory.setVirtualHost(this.virtualHost);
//        connectionFactory.setPublisherConfirms(true); //必须要设置
//        return connectionFactory;
//    }

//    @Bean("container")
//    SimpleMessageListenerContainer processContainer(ConnectionFactory connectionFactory, Consumer processReceiver) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(RabbitCfg.PROCESS_QUEUE); // 监听delay_process_queue
//        container.setMessageListener(new MessageListenerAdapter(processReceiver));
//        return container;
//    }

    @Bean("containerFactory")
    RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);

        return simpleRabbitListenerContainerFactory;
    }


    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
