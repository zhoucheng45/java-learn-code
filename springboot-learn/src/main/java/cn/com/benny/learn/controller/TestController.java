/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-15 下午3:29
 * LastModified: 18-11-15 下午3:29
 */

package cn.com.benny.learn.controller;

import cn.com.benny.learn.reqmodel.Student;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/15
 * @Time: 15:29
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/say")
    public void say(@RequestBody Student student){

    }

}
