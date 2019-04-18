/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-18 上午11:47
 * LastModified: 19-4-18 上午11:46
 */

package cn.com.benny.learn.dynamic.datasource.aop;

import cn.com.benny.learn.dynamic.datasource.DatabaseContextHolder;
import cn.com.benny.learn.dynamic.datasource.DatabaseType;
import cn.com.benny.learn.dynamic.datasource.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 21:59
 */
@Aspect
@Component
public class DataSourceAspect {


    /**
     * 使用空方法定义切点表达式
     * 定义空方法切点，便于切点复用
     */
    @Pointcut("@within(cn.com.benny.learn.dynamic.datasource.annotation.DataSource)")
    public void declareJointPointBeanExpression() {
    }

    /**
     * 使用空方法定义切点表达式
     * 定义空方法切点，便于切点复用
     */
    @Pointcut("@annotation(cn.com.benny.learn.dynamic.datasource.annotation.DataSource)")
    public void declareJointPointFunExpression() {
    }

    @Before("declareJointPointBeanExpression()")
    public void setDataSourceKey(JoinPoint point) {
        setDataSource(point);
    }

    @Before("declareJointPointFunExpression()")
    public void setDataSourceKey1(JoinPoint point) {

        setDataSource(point);
    }

    private void setDataSource(JoinPoint point) {
        Object target = point.getTarget();
        DataSource dataSource = target.getClass().getAnnotation(DataSource.class);

        Signature signature = point.getSignature();
        if (signature instanceof MethodSignature) {
            DataSource annotation = ((MethodSignature) signature).getMethod().getAnnotation(DataSource.class);
            dataSource = annotation != null ? annotation : dataSource;
        }
        if (dataSource != null) {
            String value = dataSource.value();
            DatabaseContextHolder.setDatabaseType(DatabaseType.fromName(value));
        }
    }
}
