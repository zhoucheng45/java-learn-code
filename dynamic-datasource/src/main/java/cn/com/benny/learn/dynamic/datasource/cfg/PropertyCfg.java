package cn.com.benny.learn.dynamic.datasource.cfg;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class PropertyCfg {

    /**
     * 必须设置默认的库,默认master
     */
    String primary = "master";

    /**
     * 每一个数据源的配置信息
     */
    Map<String,DataSourceProperty> datasource = new LinkedHashMap<>();

}
