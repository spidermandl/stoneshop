package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsGoodsReturn;

public interface GsGoodsReturnMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsReturn record);

    int insertSelective(GsGoodsReturn record);

    GsGoodsReturn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsReturn record);

    int updateByPrimaryKeyWithBLOBs(GsGoodsReturn record);

    int updateByPrimaryKey(GsGoodsReturn record);
}
