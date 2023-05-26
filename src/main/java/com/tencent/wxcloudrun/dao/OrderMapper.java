package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.dto.OrderRequest;
import com.tencent.wxcloudrun.model.Order;
import com.tencent.wxcloudrun.model.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO `order` (id, consignee_name, consignee_full_address, consignee_phone, total_price, discount_price, settlement_price, item_amount) " +
            "VALUES (#{order.id}, #{order.consigneeName}, #{order.consigneeFullAddress}, #{order.consigneePhone}, #{order.totalPrice}, #{order.discountPrice}, " +
            "#{order.settlementPrice}, #{order.itemAmount})")
    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    void insertOrder(@Param("order") Order order);
    @Select("SELECT * FROM `order` WHERE id = #{id}")
    Order selectOrderById(@Param("id") Integer id);

    @Select("SELECT * FROM order_item WHERE order_id = #{orderId}")
    List<OrderItem> selectOrderItemsByOrderId(@Param("orderId") Integer orderId);
}
