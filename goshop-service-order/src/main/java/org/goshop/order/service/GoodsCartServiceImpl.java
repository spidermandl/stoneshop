package org.goshop.order.service;

import com.github.pagehelper.PageInfo;
import org.goshop.common.utils.PageUtils;
import org.goshop.goods.i.GoodsService;
import org.goshop.goods.pojo.GsGoodsSpecProperty;
import org.goshop.order.i.GoodsCartService;
import org.goshop.order.mapper.master.GsCartGspMapper;
import org.goshop.order.mapper.master.GsGoodsCartMapper;
import org.goshop.order.mapper.read.ReadGsCartGspMapper;
import org.goshop.order.mapper.read.ReadGsGoodsCartMapper;
import org.goshop.order.mapper.read.ReadGsOrderformMapper;
import org.goshop.order.pojo.GsCartGsp;
import org.goshop.order.pojo.GsGoodsCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 03/01/2018.
 */
@Service("goodsCartService")
public class GoodsCartServiceImpl implements GoodsCartService{

    @Autowired
    GsGoodsCartMapper gsGoodsCartMapper;
    @Autowired
    GsCartGspMapper gsCartGspMapper;
    @Autowired
    ReadGsGoodsCartMapper readGsGoodsCartMapper;
    @Autowired
    ReadGsCartGspMapper readGsCartGspMapper;

    @Autowired
    GoodsService goodsService;

    @Override
    public int delete(Long id) {
        gsCartGspMapper.deleteByCartId(id);
        return gsGoodsCartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(GsGoodsCart goodsCart) {
        return gsGoodsCartMapper.updateByPrimaryKey(goodsCart);
    }

    @Override
    public List<GsGoodsCart> findByCondition(Map condition) {
        return readGsGoodsCartMapper.selectByCondition(condition);
    }

    @Override
    public PageInfo<GsGoodsCart> findByCondition(Map condition, int curPage, int pageSize) {
        PageUtils.startPage(curPage,pageSize);
        List<GsGoodsCart> list = readGsGoodsCartMapper.selectByCondition(condition);
        return new PageInfo<>(list);
    }

    @Override
    public GsGoodsCart findOne(Long id) {
        return readGsGoodsCartMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(GsGoodsCart goodsCart) {
        if (goodsCart.getDeletestatus()==null)
            goodsCart.setDeletestatus(false);
        return gsGoodsCartMapper.insert(goodsCart);
    }

    @Override
    public List<GsGoodsSpecProperty> findSpecPropertByGoodsCartId(Long id) {
        List<GsCartGsp> cartGsps = readGsCartGspMapper.selectByCartId(id);
        List<Long> ids = new ArrayList<>();
        for (GsCartGsp gsp: cartGsps){
            ids.add(gsp.getGspId());
        }
        return goodsService.findSpecPropByIds(ids);
    }

    @Override
    public int saveCartLinksWithSpecProperty(List<GsCartGsp> cart_gsp) {
        return gsCartGspMapper.insertBatch(cart_gsp);
    }



}
