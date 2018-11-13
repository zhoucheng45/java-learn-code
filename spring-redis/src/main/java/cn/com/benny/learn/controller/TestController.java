/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-12 下午7:09
 * LastModified: 18-11-12 下午7:09
 */

package cn.com.benny.learn.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/12
 * @Time: 19:09
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/say")
    public String say(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Long benny121 = redisTemplate.opsForValue().increment("benny12");
        Object benny12 = redisTemplate.opsForValue().get("benny");
        return String.valueOf(12);
    }
}
