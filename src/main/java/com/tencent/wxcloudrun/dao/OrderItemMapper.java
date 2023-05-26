package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.dto.OrderItemRequest;
import com.tencent.wxcloudrun.model.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderItemMapper {
    @Insert("INSERT INTO order_item (id, picture, prompt, model, seed, params, custom_type, custom_param, order_id, print_status, picture_base64，amount, price, total_price) " +
            "VALUES (#{orderItem.id}, #{orderItem.picture}, #{orderItem.prompt}, #{orderItem.model}, #{orderItem.seed}, #{orderItem.params}, " +
            "#{orderItem.customType}, #{orderItem.customParam}, #{orderItem.orderId}, #{orderItem.printStatus}, #{orderItem.pictureBase64}, #{orderItem.amount}, #{orderItem.price}, #{orderItem.totalPrice})")
    @Options(useGeneratedKeys = true, keyProperty = "orderItem.id")
    void insertOrderItem(@Param("orderItem") OrderItem orderItem);
}
