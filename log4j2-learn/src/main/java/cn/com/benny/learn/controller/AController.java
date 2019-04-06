/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-29 下午5:22
 * LastModified: 18-11-29 下午5:22
 */

package cn.com.benny.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/29
 * @Time: 17:22
 */
@RestController
public class AController {

    Logger logger = LoggerFactory.getLogger(AController.class);
    Logger logger_kafka = LoggerFactory.getLogger("kafka-log4j2");

    private CountDownLatch cd = new CountDownLatch(50);
    private CountDownLatch cd1 = new CountDownLatch(50);

    @RequestMapping("/a")
    public @ResponseBody String a(@RequestParam("name") String name) throws InterruptedException {
        Date date = new Date();
//        for (int i=0; i<10000*10000;i++){
//            logger.info("收到请求，参数{}", name);
//            if(i%10000 == 0){
//                System.out.println("请求次数"+i);
//            }
//        }

        for (int j=0; j<50; j++){
            new Thread(() -> {
                for (int i=0; i<50*10;i++) {
                    logger.info("收到请求，参数{}", name);

                }
                cd.countDown();
            }).start();
        }

        cd.await();
        Date last = new Date();
        logger.info("耗时{}}", last.getTime() - date.getTime());



        Date date1 = new Date();
        for (int j=0; j<50; j++){
            new Thread(() -> {
                for (int i=0; i<50*10;i++) {
                    logger_kafka.info("收到请求，参数{}", name);
                }
                cd1.countDown();
            }).start();
        }
        cd1.await();
        Date date2 = new Date();
        logger_kafka.info("耗时{}}", date2.getTime() - date1.getTime());
        return name;

    }
}
