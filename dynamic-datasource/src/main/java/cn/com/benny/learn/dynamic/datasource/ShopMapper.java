/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-17 下午8:22
 * LastModified: 19-4-17 下午8:22
 */

package cn.com.benny.learn.dynamic.datasource;

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

    @Select("SELECT * FROM t_shop WHERE id = #{id}")
    @Results(value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "shop_name", property = "shopName")})
    public Shop getShop(@Param("id") int id);

    @Select("SELECT * FROM t_shop WHERE shop_name = #{shopName}")
    @Results(value = {@Result(id = true, column = "id", property = "id"),
            @Result(column = "shop_name", property = "shopName")})
    public Shop getShopByName(@Param("shopName") String shopName);


}
