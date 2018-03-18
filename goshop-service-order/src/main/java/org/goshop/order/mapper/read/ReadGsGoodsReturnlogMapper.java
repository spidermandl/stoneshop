package org.goshop.order.mapper.read;

import org.goshop.order.pojo.GsGoodsReturnlog;

public interface ReadGsGoodsReturnlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsReturnlog record);

    int insertSelective(GsGoodsReturnlog record);

    GsGoodsReturnlog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsReturnlog record);

    int updateByPrimaryKey(GsGoodsReturnlog record);
}
