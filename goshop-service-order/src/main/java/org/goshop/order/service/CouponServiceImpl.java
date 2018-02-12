package org.goshop.order.service;

import org.goshop.order.i.CouponService;
import org.goshop.order.mapper.master.GsCouponInfoMapper;
import org.goshop.order.mapper.read.ReadGsCouponInfoMapper;
import org.goshop.order.mapper.read.ReadGsCouponMapper;
import org.goshop.order.pojo.GsCoupon;
import org.goshop.order.pojo.GsCouponInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 11/02/2018.
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService{

    @Autowired
    ReadGsCouponInfoMapper readGsCouponInfoMapper;
    @Autowired
    GsCouponInfoMapper gsCouponInfoMapper;
    @Autowired
    ReadGsCouponMapper readGsCouponMapper;

    @Override
    public GsCouponInfo findOne(Long id) {
        return readGsCouponInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public GsCoupon findCoupon(Long id) {
        return readGsCouponMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsCouponInfo> findByCondition(Map condition) {
        return readGsCouponInfoMapper.selectByCondition(condition);
    }

    @Override
    public int update(GsCouponInfo info) {
        return gsCouponInfoMapper.updateByPrimaryKeySelective(info);
    }
}
