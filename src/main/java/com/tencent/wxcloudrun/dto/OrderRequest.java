package com.tencent.wxcloudrun.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String consignee_name;
    private String consignee_fulladdress;
    private String consignee_phone;
    private String coupon_code;
    private List<OrderItemRequest> order_items;
}
