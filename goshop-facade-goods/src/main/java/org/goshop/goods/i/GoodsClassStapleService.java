package org.goshop.goods.i;

import org.goshop.goods.pojo.GoodsClass;
import org.goshop.goods.pojo.GoodsClassStaple;
import org.goshop.goods.pojo.JsonGoodsClass;
import org.goshop.users.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public interface GoodsClassStapleService {
    List<GoodsClassStaple> findByMemberId(Long memberId);

    GoodsClassStaple findOne(Integer id);

    int checkSaveStaple(User user, GoodsClass goodsClass);

    int delete(Integer stapleId);

    JsonGoodsClass selectGoodsClassStaple(Integer stapleId);
}
