package org.goshop.order.service;

import org.goshop.order.i.OrderFormService;
import org.goshop.order.mapper.read.ReadGsOrderformMapper;
import org.goshop.order.pojo.GsOrderform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Desmond on 10/01/2018.
 */
@Service("orderFormService")
public class OrderFormServiceImpl implements OrderFormService {


    @Autowired
    ReadGsOrderformMapper readGsOrderformMapper;

    @Override
    public List<GsOrderform> findByStoreId(Long storeId) {
        return readGsOrderformMapper.selectByStoreId(storeId);
    }
}
