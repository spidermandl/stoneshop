package org.goshop.order.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.pojo.GsGoodsWithBLOBs;
import org.goshop.order.i.OrderAddressService;
import org.goshop.order.mapper.master.GsAddressMapper;
import org.goshop.order.mapper.read.ReadGsAddressMapper;
import org.goshop.order.pojo.GsAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 05/02/2018.
 */
@Service("orderAddressService")
public class OrderAddressServiceImpl implements OrderAddressService {

    @Autowired
    ReadGsAddressMapper readGsAddressMapper;
    @Autowired
    GsAddressMapper gsAddressMapper;

    @Override
    public GsAddress findOne(Long address_id) {
        return readGsAddressMapper.selectByPrimaryKey(address_id);
    }

    @Override
    public List<GsAddress> findByCondition(Map condition) {
        return readGsAddressMapper.selectByCondition(condition);
    }

    @Override
    public PageInfo<GsAddress> findByCondition(Map condition, Integer curPage, Integer pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsAddress> list = readGsAddressMapper.selectByCondition(condition);
        return new PageInfo<>(list);
    }

    @Override
    public int save(GsAddress address) {
        if (address.getDeletestatus()==null)
            address.setDeletestatus(false);
        return gsAddressMapper.insertSelective(address);
    }

    @Override
    public int update(GsAddress address) {
        return gsAddressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public int delete(Long id) {
        return gsAddressMapper.deleteByPrimaryKey(id);
    }
}
