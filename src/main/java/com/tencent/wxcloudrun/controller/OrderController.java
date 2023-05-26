package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.dto.OrderRequest;
import com.tencent.wxcloudrun.model.Order;
import com.tencent.wxcloudrun.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@RestController

public class OrderController {
    final OrderService orderService;
    final Logger logger;

    public OrderController(@Autowired OrderService orderService) {
        this.orderService = orderService;
        this.logger = LoggerFactory.getLogger(OrderController.class);
    }
    /**
     * 更新计数，自增或者清零
     * @param request {@link CounterRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/order/place")
    ApiResponse create(@RequestBody OrderRequest request) {
        logger.info("/api/order/place post request, order items: {}", request.getOrder_items().size());
        try{
            Order order = orderService.placeOrder(request);
            return ApiResponse.ok(order);
        } catch (Exception e){
            return ApiResponse.error("内部错误=>" + e.getMessage());
        }
    }

    /**
     * 获取当前订单
     * @return API response json
     */
    @GetMapping(value = "/api/orders/{id}")
    ApiResponse getOrderDetail(@PathVariable("id") Integer id) {
        logger.info("/api/order/get get order detail request");
        Order order = orderService.getOrderDetail(1);
        if(order != null){
            return ApiResponse.ok(order);
        }else{
            return ApiResponse.error("无此订单");
        }
    }

}