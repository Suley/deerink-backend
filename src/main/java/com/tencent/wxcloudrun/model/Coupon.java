package com.tencent.wxcloudrun.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class Coupon implements Serializable {

    private Integer id;

    private Integer couponType;

    private Double couponValue;

    private String couponCode;

    private Boolean isValid;

    // Getters and setters
}