package org.goshop.order.service;

import org.goshop.order.i.StoreCartService;
import org.goshop.order.mapper.master.GsGoodsCartMapper;
import org.goshop.order.mapper.master.GsStoreCartMapper;
import org.goshop.order.mapper.read.ReadGsStoreCartMapper;
import org.goshop.order.pojo.GsStoreCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 03/01/2018.
 */
@Service("storeCartService")
public class StoreCartServiceImpl implements StoreCartService {
    @Autowired
    ReadGsStoreCartMapper readGsStoreCartMapper;
    @Autowired
    GsStoreCartMapper gsStoreCartMapper;
    @Autowired
    GsGoodsCartMapper gsGoodsCartMapper;

    @Override
    public List<GsStoreCart> findOwnCartByCondition(Map condition) {
        return readGsStoreCartMapper.selectOwnCartByCondition(condition);
    }

    @Override
    public int delete(Long id) {
        gsGoodsCartMapper.deleteByStorecartId(id);
        return gsStoreCartMapper.deleteByPrimaryKey(id);
    }
}
