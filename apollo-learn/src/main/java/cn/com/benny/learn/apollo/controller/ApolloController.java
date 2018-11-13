/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-6 下午8:14
 * LastModified: 18-11-6 下午8:14
 */

package cn.com.benny.learn.apollo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/6
 * @Time: 20:14
 */
@RestController
@RequestMapping("/apollo")
@AllArgsConstructor
public class ApolloController {

    @Value("${app.name:}")
    public String app_name ;

    @RequestMapping("/test")
    public String test(){

        return this.app_name;
    }

}
