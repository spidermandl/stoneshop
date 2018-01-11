package org.goshop.goods.i;

import org.goshop.goods.pojo.GoodsClassStaple;
import org.goshop.goods.pojo.GsGoodsClass;
import org.goshop.goods.pojo.JsonGoodsClass;
import org.goshop.users.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public interface GoodsClassStapleService {
    List<GoodsClassStaple> findByMemberId(Long memberId);

    GoodsClassStaple findOne(Long id);

    int checkSaveStaple(User user, GsGoodsClass goodsClass);

    int delete(Long stapleId);

    JsonGoodsClass selectGoodsClassStaple(Long stapleId);
}
