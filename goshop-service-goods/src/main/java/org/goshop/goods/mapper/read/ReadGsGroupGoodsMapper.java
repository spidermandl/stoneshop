package org.goshop.goods.mapper.read;

import org.goshop.goods.pojo.GsGroupGoods;

import java.util.List;

public interface ReadGsGroupGoodsMapper {
    GsGroupGoods selectByPrimaryKey(Long id);

    List<GsGroupGoods> selectByGroupId(Long group_id);
}
