package org.goshop.order.i;

import com.github.pagehelper.PageInfo;
import org.goshop.goods.pojo.GsGoodsSpecProperty;
import org.goshop.order.pojo.GsCartGsp;
import org.goshop.order.pojo.GsGoodsCart;

import java.util.List;
import java.util.Map;

/**
 * Created by Desmond on 03/01/2018.
 */
public interface GoodsCartService {

    int delete(Long id);

    int update(GsGoodsCart goodsCart);

    public List<GsGoodsCart> findByCondition(Map condition);

    public PageInfo<GsGoodsCart> findByCondition(Map condition, int curPage, int pageSize);

    GsGoodsCart findOne(Long id);

    int save (GsGoodsCart goodsCart);

    List<GsGoodsSpecProperty> findSpecPropertByGoodsCartId(Long id);

    int saveCartLinksWithSpecProperty(List<GsCartGsp> cart_gsp);

}
