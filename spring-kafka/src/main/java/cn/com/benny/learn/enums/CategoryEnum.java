/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-12-22 下午8:51
 * LastModified: 18-12-22 下午8:51
 */

package cn.com.benny.learn.enums;

import lombok.Getter;

/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/12/22
 * @Time: 20:51
 */
@Getter
public enum  CategoryEnum {

    ELECTRONIC("1001","电子产品"),
    BOOK("2001","书籍"),
    CLOTHING_MALE("3001","服装男"),
    CLOTHING_FEMALE("3002","服装女"),
    FURNITURE("4001","家居类"),
    DAILY("5001","日用平"),
    KITCHEN_WARE("6001","厨房用品"),
    ;
    String code;
    String remark;
    CategoryEnum(String code, String remark){
        this.code = code;
        this.remark = remark;
    }


    public static CategoryEnum fromCode(String code){

        for (CategoryEnum value : values()) {
            if (value.code.equals(code))
                return value;
        }
        return null;

    }

}
