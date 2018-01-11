package org.goshop.order.service;

import org.goshop.order.i.GoodsCartService;
import org.goshop.order.mapper.master.GsGoodsCartMapper;
import org.goshop.order.pojo.GsGoodsCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Desmond on 03/01/2018.
 */
@Service("goodsCartService")
public class GoodsCartServiceImpl implements GoodsCartService{

    @Autowired
    GsGoodsCartMapper gsGoodsCartMapper;

    @Override
    public int delete(Long id) {
        return gsGoodsCartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(GsGoodsCart goodsCart) {
        return gsGoodsCartMapper.updateByPrimaryKey(goodsCart);
    }


}
