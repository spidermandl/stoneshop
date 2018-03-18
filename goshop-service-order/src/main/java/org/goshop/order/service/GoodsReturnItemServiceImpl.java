package org.goshop.order.service;

import org.goshop.goods.pojo.GsGoodsSpecProperty;
import org.goshop.order.i.GoodsCartService;
import org.goshop.order.i.GoodsReturnItemService;
import org.goshop.order.mapper.master.GsGoodsCartMapper;
import org.goshop.order.mapper.master.GsGoodsReturnGspMapper;
import org.goshop.order.mapper.master.GsGoodsReturnitemMapper;
import org.goshop.order.mapper.read.ReadGsGoodsCartMapper;
import org.goshop.order.pojo.GsGoodsCart;
import org.goshop.order.pojo.GsGoodsReturnGsp;
import org.goshop.order.pojo.GsGoodsReturnitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desmond on 02/03/2018.
 */
@Service("goodsReturnItemService")
public class GoodsReturnItemServiceImpl implements GoodsReturnItemService {

    @Autowired
    GsGoodsReturnitemMapper gsGoodsReturnitemMapper;
    @Autowired
    GsGoodsReturnGspMapper gsGoodsReturnGspMapper;
    @Autowired
    GoodsCartService goodsCartService;

    @Override
    public Long save(GsGoodsReturnitem item, GsGoodsCart gc) {
        gsGoodsReturnitemMapper.insertSelective(item);
        if (gc!=null) {
            List<GsGoodsSpecProperty> gsps = this.goodsCartService.findSpecPropertByGoodsCartId(gc.getId());
            List<GsGoodsReturnGsp> return_gsps = new ArrayList<>();
            for (GsGoodsSpecProperty gsp : gsps){
                GsGoodsReturnGsp g = new GsGoodsReturnGsp();
                g.setItemId(item.getId());
                g.setGspId(gsp.getId());
                return_gsps.add(g);
            }
            gsGoodsReturnGspMapper.insertBatch(return_gsps);
        }
        return item.getId();
    }
}
