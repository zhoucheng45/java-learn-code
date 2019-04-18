/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-17 下午8:23
 * LastModified: 19-4-17 下午8:23
 */

package cn.com.benny.learn.dynamic.datasource;

import cn.com.benny.learn.dynamic.datasource.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 20:23
 */
@Repository
@DataSource("mytestdb2")
public class ShopDao {
    @Autowired
    ShopMapper shopMapper;

    @DataSource("mytestdb")
    public Shop getShop(int id){
        return shopMapper.getShop(id);
    }

//    @DataSource("mytestdb2")
    public Shop getShop(String name){
        return shopMapper.getShopByName(name);
    }
}
