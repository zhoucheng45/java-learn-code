package cn.com.benny.learn.models;

import java.io.Serializable;
import java.util.Date;

public class TestOrder implements Serializable {
    private Integer id;

    private String payer;

    private String payee;

    private String orderNo;

    private Integer amont;

    private Integer actAmont;

    private String category;

    private String remark;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer == null ? null : payer.trim();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee == null ? null : payee.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getAmont() {
        return amont;
    }

    public void setAmont(Integer amont) {
        this.amont = amont;
    }

    public Integer getActAmont() {
        return actAmont;
    }

    public void setActAmont(Integer actAmont) {
        this.actAmont = actAmont;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}