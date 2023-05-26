package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.CouponMapper;
import com.tencent.wxcloudrun.dao.OrderItemMapper;
import com.tencent.wxcloudrun.dao.OrderMapper;
import com.tencent.wxcloudrun.dto.OrderItemRequest;
import com.tencent.wxcloudrun.dto.OrderRequest;
import com.tencent.wxcloudrun.model.Coupon;
import com.tencent.wxcloudrun.model.Order;
import com.tencent.wxcloudrun.model.OrderItem;
import com.tencent.wxcloudrun.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    final CouponMapper couponMapper;
    final OrderMapper orderMapper;
    final OrderItemMapper orderItemMapper;

    public OrderServiceImpl(@Autowired CouponMapper couponMapper, @Autowired OrderItemMapper orderItemMapper, @Autowired OrderMapper orderMapper) {
        this.couponMapper = couponMapper;
        this.orderItemMapper = orderItemMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        Double couponValue = 0.00;
        Double totalPrice = 0.00;
        Double settlementPrice = 0.00;
        Integer itemAmount = 0;
        if(!orderRequest.getCoupon_code().isEmpty()){
            Coupon coupon = couponMapper.getCoupon(orderRequest.getCoupon_code());
            if(coupon != null){
                couponValue = coupon.getCouponValue();
            }
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemRequest orderItemRequest : orderRequest.getOrder_items()){
            OrderItem orderItem = new OrderItem();
            itemAmount++;
            Double price = 0.00;
            Double itemTotalPrice = 0.00;
            if(orderItemRequest.getCustom_type().equals("tshirt")){
                price = 99.00;
                orderItem.setPrintStatus(-1);
            }else if(orderItemRequest.getCustom_type().equals("cupsticker")){
                price = 5.00;
                orderItem.setPrintStatus(0);
            }
            itemTotalPrice = price * orderItemRequest.getAmount();
            totalPrice += itemTotalPrice;
            orderItem.setPrompt(orderItemRequest.getPrompt());
            orderItem.setSeed(orderItemRequest.getSeed());
            orderItem.setParams(orderItemRequest.getParams());
            // 将amount转换为Integer类型并设置给orderItem
            orderItem.setTotalPrice(itemTotalPrice);
            orderItem.setPrice(price);
            orderItem.setAmount(orderItemRequest.getAmount());
            orderItem.setCustomType(orderItemRequest.getCustom_type());
            orderItem.setCustomParam(orderItemRequest.getCustom_param());
            orderItem.setPictureBase64(orderItemRequest.getPicture_base64());

            orderItems.add(orderItem);
        }
        settlementPrice = totalPrice - couponValue;
        order.setTotalPrice(totalPrice);
        order.setConsigneeName(orderRequest.getConsignee_name());
        order.setConsigneePhone(orderRequest.getConsignee_phone());
        order.setConsigneeFullAddress(orderRequest.getConsignee_fulladdress());
        order.setItemAmount(itemAmount);
        order.setDiscountPrice(couponValue);
        order.setSettlementPrice(settlementPrice);
        orderMapper.insertOrder(order);
        for(OrderItem orderItem : orderItems){
            orderItem.setOrderId(order.getId());
            orderItemMapper.insertOrderItem(orderItem);
        }
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public Order getOrderDetail(Integer id) {
        Order order = orderMapper.selectOrderById(id);
        if(order != null){
            order.setOrderItems(orderMapper.selectOrderItemsByOrderId(id));
        }
        return order;
    }
}
