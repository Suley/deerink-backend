package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CouponMapper {

    Coupon getCoupon(@Param("code") String code);

    void useCoupon(@Param("code") String code);
}
