/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-17 下午7:43
 * LastModified: 19-4-17 下午7:43
 */

package cn.com.benny.learn.dynamic.datasource;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * <p>DatabaseType容器，并提供了向其中设置和获取DatabaseType的方法:<p>
 * 保存一个线程安全的DatabaseType容器
 *
 * QQ: 178542285
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 19:43
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<Deque<String>> contextHolder = ThreadLocal.withInitial(() -> new ArrayDeque());

    /**
     *
     * @param type
     */
    public static void setDatabaseType(String type) {
        contextHolder.get().push(type);
    }

    public static String getDatabaseType() {
        return contextHolder.get().peek();
    }

    /**
     * 移除当前
     * @return
     */
    public static String remove(){
        return contextHolder.get().pop();
    }
}
