package com.tencent.wxcloudrun.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class OrderItem implements Serializable {

    private Integer id;

    private String picture;

    private String prompt;

    private String model;

    private String seed;

    private String params;

    private String customType;

    private String customParam;

    private Integer amount;

    private Double price;

    private Double totalPrice;

    private Integer orderId;

    private Order order;

    private Integer printStatus;

    private String pictureBase64;

}