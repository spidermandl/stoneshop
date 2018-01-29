package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGroupGoods;

public interface GsGroupGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGroupGoods record);

    int insertSelective(GsGroupGoods record);

    GsGroupGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGroupGoods record);

    int updateByPrimaryKeyWithBLOBs(GsGroupGoods record);

    int updateByPrimaryKey(GsGroupGoods record);
}
