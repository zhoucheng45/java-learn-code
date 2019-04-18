/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-17 下午7:45
 * LastModified: 19-4-17 下午7:45
 */

package cn.com.benny.learn.dynamic.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>动态数据源:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 19:45
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}
