/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-20 下午5:51
 * LastModified: 19-4-20 下午5:51
 */

package cn.com.benny.learn.dynamic.datasource.cfg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-20
 * @Time: 17:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "mybatis")
public class MyBatisProperty {
    String mapperLocations ="classpath*:*Mapper.xml";
    String typeAliasesPackage;
}
