/*
 * CopyRight benny
 * ProjectName: mybatis-generator-learn
 * Author: benny
 * Date: 18-12-22 下午8:49
 * LastModified: 18-12-22 下午8:49
 */

package cn.com.benny.learn.model;
import lombok.Data;

import java.util.Date;


/**
 * <p>Description:<p>
 * QQ: 178542285
 *
 * @Author benny
 * @Date: 2018/12/22
 * @Time: 20:49
 */
@Data
public class Order {

    String payer;
    String payee;
    String orderNo;
    int amont;
    int actAmont;
    String category;
    String remark;
    Date createTime;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"payer\":\"")
                .append(payer).append('\"');
        sb.append(",\"payee\":\"")
                .append(payee).append('\"');
        sb.append(",\"orderNo\":\"")
                .append(orderNo).append('\"');
        sb.append(",\"amont\":")
                .append(amont);
        sb.append(",\"actAmont\":")
                .append(actAmont);
        sb.append(",\"category\":\"")
                .append(category).append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime.toLocaleString()).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
