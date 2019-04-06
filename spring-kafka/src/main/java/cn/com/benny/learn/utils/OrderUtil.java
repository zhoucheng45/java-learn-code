/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-12-22 下午9:55
 * LastModified: 18-12-22 下午9:55
 */

package cn.com.benny.learn.utils;

import cn.com.benny.learn.enums.CategoryEnum;
import cn.com.benny.learn.model.Order;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/12/22
 * @Time: 21:55
 */
public class OrderUtil {
    static Random random = new Random();
    static final String[] categoryCodes = new String[]{"1001","2001","3001","3002","4001","5001","6001"};

    public static String generatorOrderNo() {
        return UUID.randomUUID().toString();
    }

    public static String generatorPayerOrPayee() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static CategoryEnum generatorCategory(){
        Random random = new Random();
        int i = random.nextInt(categoryCodes.length);
        return CategoryEnum.fromCode(categoryCodes[i]);
    }

    public static int generatorAmont(){
        return random.nextInt(100)+1;
    }

    public static int generatorActAmont(int amont){
        return random.nextInt(amont)+1;
    }

    public static void main(String[] args) {
        System.out.println(generatorOrder());
    }

    public static Order generatorOrder(){
        Order order = new Order();
        CategoryEnum categoryEnum = generatorCategory();
        order.setOrderNo(generatorOrderNo());
        order.setAmont(generatorAmont());
        order.setActAmont(generatorActAmont(order.getAmont()));
        order.setCategory(categoryEnum.getCode());
        order.setRemark(categoryEnum.getRemark());
        order.setPayee(generatorPayerOrPayee());
        order.setPayer(generatorPayerOrPayee());
        order.setCreateTime(new Date());

        return order;
    }

}
