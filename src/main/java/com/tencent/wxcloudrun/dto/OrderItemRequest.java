package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private String prompt;
    private String seed;
    private String params;
    private Integer amount;
    private String custom_type;
    private String custom_param;
    private String picture_base64;
}
