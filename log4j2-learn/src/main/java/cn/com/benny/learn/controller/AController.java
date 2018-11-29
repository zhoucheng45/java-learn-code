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

    @RequestMapping("/a")
    public @ResponseBody String a(@RequestParam("name") String name){
        logger.info("收到请求，参数{}", name);

        return name;

    }
}
