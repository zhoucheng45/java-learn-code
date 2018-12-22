/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-21 下午5:54
 * LastModified: 18-11-21 下午5:54
 */

package cn.com.benny.learn.cache;

import com.google.common.cache.*;
import com.google.common.graph.Graph;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/21
 * @Time: 17:54
 */
public class CacheApp {

    public static void main(String[] args) {

        LoadingCache<String, SimpleDateFormat> dfCache = CacheBuilder.newBuilder()
                .maximumSize(100).expireAfterAccess(1, TimeUnit.MINUTES)
                .removalListener((RemovalListener<String, SimpleDateFormat>) notification -> {
                    String key = notification.getKey();
                    System.out.println("pattern: " + key + " 被移除");
                }).build(
                        new CacheLoader<String, SimpleDateFormat>() {
                            @Override
                            public SimpleDateFormat load(String key) throws Exception {

                                return new SimpleDateFormat(key);
                            }
                        }
                );

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");



        String format = dateFormat.format(new Date());
        System.out.println(format);


    }
}
