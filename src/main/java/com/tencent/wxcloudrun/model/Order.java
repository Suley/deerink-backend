package com.tencent.wxcloudrun.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class Order implements Serializable {

    public Order() {
        // 无参构造函数
    }
    private Integer id;

    private String consigneeName;

    private String consigneeFullAddress;

    private String consigneePhone;

    private String couponCode;

    private Double totalPrice;

    private Double discountPrice;

    private Double settlementPrice;

    private Integer itemAmount;

    private Integer isPaid;

    private List<OrderItem> orderItems;
    // Getters and setters
}