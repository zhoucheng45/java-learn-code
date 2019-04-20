/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-18 上午11:47
 * LastModified: 19-4-17 下午9:28
 */

package cn.com.benny.learn.dynamic.datasource.cfg;

import cn.com.benny.learn.dynamic.datasource.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 19:49
 */
@Configuration
public class DataSourceCfg {

    @Autowired
    MyBatisProperty myBatisProperty;

    /**
     * @return
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例
     */
    @Bean("dynamicDataSource")
    @Order
    public DynamicDataSource dynamicDataSource(@Autowired PropertyCfg propertyCfg ) throws Exception {
        Map<Object,Object> dataSourceHashMap = new HashMap<>(8);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        for (Map.Entry<String, DataSourceProperty> e : propertyCfg.datasource.entrySet()) {
            DataSourceProperty dataSourceProperty = e.getValue();
            Properties props = new Properties();
            props.put("driverClassName", dataSourceProperty.driverClassName);
            props.put("url", dataSourceProperty.url);
            props.put("username", dataSourceProperty.username);
            props.put("password", dataSourceProperty.password);

            DataSource dataSource = DruidDataSourceFactory.createDataSource(props);

            dataSourceHashMap.put(e.getKey(), dataSource);

        }
        dynamicDataSource.setTargetDataSources(dataSourceHashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceHashMap.get(propertyCfg.primary));
        return dynamicDataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dynamicDataSource);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加

        fb.setTypeAliasesPackage(myBatisProperty.typeAliasesPackage);// 指定基包
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(myBatisProperty.mapperLocations));//

        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
