/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-18 上午11:46
 * LastModified: 19-4-18 上午10:30
 */

package cn.com.benny.learn.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 22:24
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /**
     * 数据源名称
     * @return
     */
    String value();
}
