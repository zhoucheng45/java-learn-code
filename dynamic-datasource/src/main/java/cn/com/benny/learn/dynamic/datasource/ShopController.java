/*
 * CopyRight benny
 * ProjectName: java-learn-code
 * Author: benny
 * Date: 19-4-17 下午9:41
 * LastModified: 19-4-17 下午9:41
 */

package cn.com.benny.learn.dynamic.datasource;

import cn.com.benny.learn.dynamic.datasource.dao.ShopDao;
import cn.com.benny.learn.dynamic.datasource.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2019-04-17
 * @Time: 21:41
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopDao shopDao;

    @RequestMapping("/get")
    public Shop get(@RequestParam("id") Integer id) {
        Shop shop = shopDao.getShop(id);
        return shop;
    }


    @RequestMapping("/getByName")
    public Shop getByName(@RequestParam("name") String name) {
        Shop shop = shopDao.getShop(name);
        return shop;
    }
}
