/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 18-11-6 下午7:57
 * LastModified: 18-11-6 下午7:57
 */

package cn.com.benny.learn.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/11/6
 * @Time: 19:57
 */
@SpringBootApplication
@EnableApolloConfig
public class ApolloApplication {

    @ApolloConfig("")
    private Config apolloConfig;

    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class,args);
    }
}
