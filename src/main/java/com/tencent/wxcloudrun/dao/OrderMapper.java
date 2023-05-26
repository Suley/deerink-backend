package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.dto.OrderRequest;
import com.tencent.wxcloudrun.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO `order` (id, consignee_name, consignee_full_address, consignee_phone, total_price, discount_price, settlement_price, item_amount) " +
            "VALUES (#{order.id}, #{order.consigneeName}, #{order.consigneeFullAddress}, #{order.consigneePhone}, #{order.totalPrice}, #{order.discountPrice}, " +
            "#{order.settlementPrice}, #{order.itemAmount})")
    @Options(useGeneratedKeys = true, keyProperty = "order.id")
    void insertOrder(@Param("order") Order order);
}
