/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-21 下午6:18
 * LastModified: 18-11-21 下午6:18
 */

package cn.com.benny.learn.common.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.*;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/21
 * @Time: 18:18
 */
public class DateUtils {

    /**
     * DateFormat 缓存
     */
    private static final LoadingCache<String, SimpleDateFormat> dfCache = CacheBuilder.newBuilder()
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

    public static String yyyy_MM_dd = "yyyy-MM-dd";
    public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static String[] patterns = {yyyy_MM_dd, yyyy_MM_dd_HH_mm_ss};

    static {
        Arrays.stream(patterns).forEach(item -> dfCache.put(item, new SimpleDateFormat(item)));
    }

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(20, 100, 1, TimeUnit.MINUTES,
                new SynchronousQueue(), (r, executor) -> {

        });
        final Date date = new Date();
//        System.out.println(date.getTime());
        for (int i = 0; i < 10000 * 1000; i++) {
            es.submit(() -> {

                try {
                    SimpleDateFormat dateFormat = dfCache.get(yyyy_MM_dd_HH_mm_ss);
//                    SimpleDateFormat dateFormat = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
                    dateFormat.format(date);
//                    System.out.println(dateFormat);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(new Date().getTime() - date.getTime());

    }


}
