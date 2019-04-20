/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-20 下午5:09
 * LastModified: 19-4-20 下午5:06
 */

package cn.com.benny.learn.dynamic.datasource.dao;

import cn.com.benny.learn.dynamic.datasource.model.Shop;
import org.apache.ibatis.annotations.*;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 20:22
 */
@Mapper
public interface ShopMapper {

    @Select("SELECT * FROM shop WHERE id = #{id}")
    @Results(value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "shop_name", property = "shopName")})
    Shop getShop(@Param("id") int id);

    @Select("SELECT * FROM shop WHERE shop_name = #{shopName}")
    @Results(value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "shop_name", property = "shopName")})
    Shop getShopByName(@Param("shopName") String shopName);


    Shop selectById(@Param("id") int id);
}
