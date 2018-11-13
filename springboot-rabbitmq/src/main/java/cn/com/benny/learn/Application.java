/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-11-13 下午11:14
 * LastModified: 18-11-13 下午11:14
 */

package cn.com.benny.learn;

import org.springframework.amqp.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    final static String queueName = "helloQueue";

    @Bean
    public Queue helloQueue() {
        return new Queue(queueName);
    }
    
    @Bean
    public Queue userQueue() {
        return new Queue("user");
    }
    
    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue("topic.message");
    }

    @Bean
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }
  //===============以上是验证topic Exchange的队列==========
    
    
    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CMessage() {
        return new Queue("fanout.C");
    }
    //===============以上是验证Fanout Exchange的队列==========

    /**
     * direct exchange 一对一， 消息中的routkey与queue中的routkey 匹配转发
     * @return
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    /**
     * top exchange,通配路由,是direct exchange的通配符模式，消息中的routkey可以写成通配的模式，exchange
     *    支持“#”和“*” 的通配,收到消息后，将消息转发给所有符合匹配表达式的queue
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * fanout exchange，将消息转发给绑定在该exchange上的所有queue
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将队列topic.message与topicExchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
    
    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
    
    
   
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}