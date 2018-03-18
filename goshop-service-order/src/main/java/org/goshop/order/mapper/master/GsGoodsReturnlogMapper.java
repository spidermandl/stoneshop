package org.goshop.order.mapper.master;

import org.goshop.order.pojo.GsGoodsReturnlog;

public interface GsGoodsReturnlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsReturnlog record);

    int insertSelective(GsGoodsReturnlog record);

    GsGoodsReturnlog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsReturnlog record);

    int updateByPrimaryKey(GsGoodsReturnlog record);
}
