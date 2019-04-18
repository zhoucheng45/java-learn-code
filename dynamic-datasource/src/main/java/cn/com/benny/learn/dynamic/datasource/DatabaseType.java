/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-17 下午7:41
 * LastModified: 19-4-17 下午7:41
 */

package cn.com.benny.learn.dynamic.datasource;

import lombok.ToString;

import java.util.Arrays;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 19:41
 */
@ToString
public enum DatabaseType {
    mytestdb("mytestdb"),
    mytestdb2("mytestdb2"),
    ;
    String name;
    DatabaseType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static DatabaseType fromName(String name){
        DatabaseType[] values = values();
        for (DatabaseType value : values) {
            if(value.getName().equals(name)){
                return value;
            }
        }
        return null;
    }
}
