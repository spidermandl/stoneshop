package org.goshop.order.service;

import org.goshop.order.i.OrderFormService;
import org.goshop.order.mapper.master.GsOrderformMapper;
import org.goshop.order.mapper.read.ReadGsOrderformMapper;
import org.goshop.order.pojo.GsOrderform;
import org.goshop.order.pojo.GsOrderformWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 10/01/2018.
 */
@Service("orderFormService")
public class OrderFormServiceImpl implements OrderFormService{

    @Autowired
    ReadGsOrderformMapper readGsOrderformMapper;
    @Autowired
    GsOrderformMapper gsOrderformMapper;


    @Override
    public GsOrderformWithBLOBs findOne(Long id) {
        return readGsOrderformMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GsOrderform> findByStoreId(Long storeId) {
        return readGsOrderformMapper.selectByStoreId(storeId);
    }

    @Override
    public int save(GsOrderformWithBLOBs form) {
        if (form.getDeletestatus()==null)
            form.setDeletestatus(false);
        return gsOrderformMapper.insertSelective(form);
    }

    @Override
    public int update(GsOrderformWithBLOBs form) {
        return gsOrderformMapper.insertSelective(form);
    }

    @Override
    public int delete(Long id) {
        return gsOrderformMapper.deleteByPrimaryKey(id);
    }
}
