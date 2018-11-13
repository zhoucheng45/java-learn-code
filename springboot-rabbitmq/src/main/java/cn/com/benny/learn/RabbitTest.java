/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-11-13 下午11:21
 * LastModified: 18-11-13 下午11:21
 */

package cn.com.benny.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
    
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;
    
    @GetMapping("/hello")
    public void hello() {
        helloSender1.send();
    }
}