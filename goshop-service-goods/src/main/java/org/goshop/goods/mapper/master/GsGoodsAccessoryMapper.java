package org.goshop.goods.mapper.master;

import org.goshop.goods.pojo.GsGoodsAccessory;

public interface GsGoodsAccessoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GsGoodsAccessory record);

    int insertSelective(GsGoodsAccessory record);

    GsGoodsAccessory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GsGoodsAccessory record);

    int updateByPrimaryKey(GsGoodsAccessory record);
}
