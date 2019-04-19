package cn.com.benny.learn.dynamic.datasource.cfg;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DataSourceProperty {

    String url;
    String driverClassName;
    String username;
    String password;

}
